package nc.block.reactor;

import nc.NuclearCraft;
import nc.block.NCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

public class BlockReactorBlock extends Block {

    public BlockReactorBlock() {
        super(Material.iron);
    }

    @SideOnly(Side.CLIENT)
    protected IIcon blockIcons[];

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {

        blockIcons = new IIcon[2];
        for (int i = 0; i < blockIcons.length; i++) {
            blockIcons[i] = p_149651_1_.registerIcon("nc:reactor/" + this.getUnlocalizedName().substring(5) + "_" + i + (NuclearCraft.alternateCasing ? "_Alt" : ""));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {

        return blockIcons[p_149691_2_];
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
}
