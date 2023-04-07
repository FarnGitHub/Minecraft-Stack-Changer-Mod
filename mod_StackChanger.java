package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class mod_StackChanger extends BaseMod {
	private File optionsFile;

	public void ModsLoaded() {
		this.optionsFile = new File(ModLoader.getMinecraftInstance().getMinecraftDir() , "/config/cutomstacksizelist.txt");
		this.loadOptions();			
	}

	public String toString() {
		return "Stack Changer ( " + this.Version() + " )";
	}

	public String Name() {
		return "Stack Changer";
	}

	public String Description() {
		return "Allowed you to change every item's maximum stacks size";
	}

	public String Version() {
		return "v1.0";
	}

	public void loadOptions() {
		try {
			if(!this.optionsFile.exists()) {
				this.WriteOptions();
			}

			BufferedReader var1 = new BufferedReader(new FileReader(this.optionsFile));
			String var2 = "";

			while(true) {
				var2 = var1.readLine();
				if(var2 == null) {
					var1.close();
					break;
				}

				try {
					if(!var2.startsWith("#") && var2.length() >= 2) {
						String[] var3 = var2.split("=");
						if(Item.itemsList[Integer.parseInt(var3[0])] != null) {
							Item parseditem = Item.itemsList[Integer.parseInt(var3[0])];
							parseditem.maxStackSize = Integer.parseInt(var3[1]);
						} else {
							System.out.println("(Stack Changer Error) " + Integer.parseInt(var3[0]) + " is not valid item id");								
						}
					}
				} catch (Exception e) {
					System.out.println("(Stack Changer Error) " + e);
				}
			}
		} catch (Exception e2) {
			System.out.println("(Stack Changer Error) " + e2);
		}
	}

	private void WriteOptions() {
		try {
			PrintWriter var1 = new PrintWriter(new FileWriter(this.optionsFile));
			var1.println("# This file allowed you to change item and block maximum stack size");	
			var1.println("#");
			var1.println("# Parameter : ItemID/BlockID=StackSize");	
			var1.println("#");	
			var1.println("# Example");
			var1.println("357=8");			
			var1.close();
		} catch (Exception var3) {
			var3.printStackTrace();
		}
	}	
}
