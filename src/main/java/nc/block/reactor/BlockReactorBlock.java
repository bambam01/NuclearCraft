package nc.block.reactor;

import nc.NuclearCraft;
import nc.render.SidedSubmapManagerCTM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import team.chisel.ctmlib.ICTMBlock;
import team.chisel.ctmlib.ISubmapManager;
import team.chisel.ctmlib.SubmapManagerCTM;

import java.util.List;

public class BlockReactorBlock extends Block implements ICTMBlock<ISubmapManager> {



    @SideOnly(Side.CLIENT)
    private SidedSubmapManagerCTM normalManager;

    @SideOnly(Side.CLIENT)
    private SidedSubmapManagerCTM glassManager;



    public BlockReactorBlock() {
        super(Material.iron);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return NuclearCraft.connectedRenderID;
    }



    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {

        glassManager = new SidedSubmapManagerCTM("reactor/" + this.getUnlocalizedName().substring(5) + "_glass" + (NuclearCraft.alternateCasing ? "Alt" : ""));
        glassManager.registerIcons(NuclearCraft.textureid, this, icon);
        normalManager = new SidedSubmapManagerCTM("reactor/" + this.getUnlocalizedName().substring(5) + (NuclearCraft.alternateCasing ? "Alt" : ""));
        normalManager.registerIcons(NuclearCraft.textureid, this, icon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        switch (world.getBlockMetadata(x - Facing.offsetsXForSide[side], y - Facing.offsetsYForSide[side], z - Facing.offsetsZForSide[side])){
            case 1:
                return glassManager.getIcon(side, 1);
            case 0:
            default:
                return normalManager.getIcon(side, 0);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        switch (meta){
            case 1:
                return glassManager.getIcon(side, meta);
            case 0:
            default:
                return normalManager.getIcon(side, meta);
        }

    }

    @Override
    public int damageDropped(int par1) {
        return par1;

    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {

        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);

        return p_149646_1_.getBlockMetadata(p_149646_2_ - Facing.offsetsXForSide[p_149646_5_], p_149646_3_ - Facing.offsetsYForSide[p_149646_5_], p_149646_4_ - Facing.offsetsZForSide[p_149646_5_]) != 1 || p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) != 1 || block != this;


    }

    @Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        for (int i = 0; i < 2; i++) {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public ISubmapManager getManager(IBlockAccess world, int x, int y, int z, int meta) {
        switch (meta){
            case 0:
                return normalManager;
            case 1:
            default:
                return glassManager;
        }
    }

    @Override
    public ISubmapManager getManager(int meta) {
        switch (meta){
            case 0:
                return normalManager;
            case 1:
            default:
                return glassManager;
        }
    }
}
