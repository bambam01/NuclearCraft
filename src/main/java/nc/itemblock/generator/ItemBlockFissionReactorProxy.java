package nc.itemblock.generator;

import nc.itemblock.ItemBlockNC;
import net.minecraft.block.Block;

public class ItemBlockFissionReactorProxy extends ItemBlockNC {

	public ItemBlockFissionReactorProxy(Block block) {
		super(block, "Acts as a Proxy to the fission reactor controller", "Rightclick to open controller interface", "Connect cables/pipes for energy/steam/items", "Redstone applied to this block has no effect on the reactor");
	}
}