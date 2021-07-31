package nc.itemblock.reactor;

import nc.itemblock.ItemBlockMeta;
import nc.itemblock.ItemBlockNC;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockReactorBlock extends ItemBlockMeta {

	public ItemBlockReactorBlock(Block block) {
		super(block);
	}


	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "_" + itemstack.getItemDamage();
	}
}
