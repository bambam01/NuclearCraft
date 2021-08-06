package nc.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import team.chisel.ctmlib.ISubmapManager;
import team.chisel.ctmlib.RenderBlocksCTM;
import team.chisel.ctmlib.TextureSubmap;

/**
 * A convenience implementation of {@link ISubmapManager} which does the standard CTM behavior.
 */
public class SidedSubmapManagerCTM implements ISubmapManager {

    @SideOnly(Side.CLIENT)
    private static SidedRenderBlocksCTM rb;

    public TextureSubmap getSubmap() {
        return submap;
    }

    public TextureSubmap getSubmapSmall() {
        return submapSmall;
    }

    public String getTextureName() {
        return textureName;
    }

    private TextureSubmap submap, submapSmall, submapbottom, submaptop, submapSmalltop , submapSmallbottom;

    private final String textureName;

    /**
     * @param textureName
     *            The path to the texture, excluding the mod ID (this is passed into {@link #registerIcons(String, Block, IIconRegister)}).
     *            <p>
     *            The CTM texture will be this path, plus {@code "-ctm"}.
     */
    public SidedSubmapManagerCTM(String textureName) {
        this.textureName = textureName;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return submapSmall.getBaseIcon();
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return getIcon(side, world.getBlockMetadata(x, y, z));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(String modName, Block block, IIconRegister register) {
        submap = new TextureSubmap(register.registerIcon(modName + ":" + textureName + "-ctm"), 4, 4);
        submapSmall = new TextureSubmap(register.registerIcon(modName + ":" + textureName), 2, 2);
    }

    @SideOnly(Side.CLIENT)
    public void registerIconsTop(String modName, Block block, IIconRegister register) {
        submaptop = new TextureSubmap(register.registerIcon(modName + ":" + textureName + "-top-ctm"), 4, 4);
        submapSmalltop = new TextureSubmap(register.registerIcon(modName + ":" + textureName + "-top"), 2, 2);
    }

    @SideOnly(Side.CLIENT)
    public void registerIconsBottom(String modName, Block block, IIconRegister register) {
        submapbottom = new TextureSubmap(register.registerIcon(modName + ":" + textureName + "-bottom-ctm"), 4, 4);
        submapSmallbottom = new TextureSubmap(register.registerIcon(modName + ":" + textureName + "-bottom"), 2, 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderBlocks createRenderContext(RenderBlocks rendererOld, Block block, IBlockAccess world) {
        if (rb == null) {
            rb = new SidedRenderBlocksCTM();
        }
        rb.setRenderBoundsFromBlock(block);
        rb.submap = submap;
        rb.submapSmall = submapSmall;
        rb.submaptop = submaptop;
        rb.submapSmalltop = submapSmalltop;
        rb.submapbottom = submapbottom;
        rb.submapSmallbottom = submapSmallbottom;
        rb.manager = this;
        return rb;
    }

    @Override
    public void preRenderSide(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    }

    @Override
    public void postRenderSide(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    }
}
