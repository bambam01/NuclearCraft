package nc.block.accelerator;

import java.util.Random;

import nc.NuclearCraft;
import nc.block.NCBlocks;
import nc.render.SidedSubmapManagerCTM;
import nc.tile.accelerator.TileSupercooler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import team.chisel.ctmlib.ICTMBlock;
import team.chisel.ctmlib.ISubmapManager;

public class BlockSupercooler extends BlockContainer  implements ICTMBlock<ISubmapManager> {
	private final boolean isActive;

	@SideOnly(Side.CLIENT)
	private SidedSubmapManagerCTM manager;
	
	public BlockSupercooler(boolean isActive) {
	super(Material.iron);
	this.isActive = isActive;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return NuclearCraft.connectedRenderID;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		manager = new SidedSubmapManagerCTM("accelerator/supercooler/" + (this.isActive ? "Active" : "Idle"));
		manager.registerIcons(NuclearCraft.textureid, this, iconRegister);
		manager.registerIconsTop(NuclearCraft.textureid, this, iconRegister);
		manager.registerIconsBottom(NuclearCraft.textureid, this, iconRegister);

	}

	public Item getItemDropped(int par1, Random random, int par3) {
		return Item.getItemFromBlock(NCBlocks.supercoolerIdle);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return manager.getIcon(world,x,y,z,side);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return manager.getIcon(side, meta);

	}
	
	public TileEntity createNewTileEntity(World world, int par1) {
		return new TileSupercooler();
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemstack) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		if (itemstack.hasDisplayName()) {
			((TileSupercooler)world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}
	}
	
	public static void updateBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		
		if(active) {
			worldObj.setBlock(xCoord, yCoord, zCoord, NCBlocks.supercoolerActive);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord, NCBlocks.supercoolerIdle);
		}
		
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}
	
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(NCBlocks.supercoolerIdle);
	}

	@Override
	public ISubmapManager getManager(IBlockAccess world, int x, int y, int z, int meta) {
		return manager;
	}

	@Override
	public ISubmapManager getManager(int meta) {
		return manager;
	}
}