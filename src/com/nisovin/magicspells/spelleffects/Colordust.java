package com.nisovin.magicspells.spelleffects;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

class Colordust
  extends SpellEffect
{
  int red = 0;
  int green = 0;
  int blue = 0;
  float xSpread = 0.0F;
  float ySpread = 0.0F;
  float zSpread = 0.0F;
  float yOffset = 0.0F;
  float fOffset = 0.0F;
  float sOffset = 0.0F;
  float speed = 0.0F;
  int count = 5;
  int visibleRange = 32;
  
  public void loadFromString(String string)
  {
    if ((string != null) && (!string.isEmpty()))
    {
      String[] data = string.split(" ");
      if (data.length >= 1) {
        this.xSpread = Float.parseFloat(data[0]);
      }
      if (data.length >= 2) {
        this.ySpread = Float.parseFloat(data[1]);
      }
      if (data.length >= 3) {
        this.speed = Float.parseFloat(data[2]);
      }
      if (data.length >= 4) {
        this.count = Integer.parseInt(data[3]);
      }
      if (data.length >= 5) {
        this.zSpread = Integer.parseInt(data[4]);
      }
      if (data.length >= 6) {
        this.yOffset = Integer.parseInt(data[5]);
      }
      if (data.length >= 7) {
        this.fOffset = Integer.parseInt(data[6]);
      }
      if (data.length >= 8) {
        this.sOffset = Integer.parseInt(data[7]);
      }
      if (data.length >= 9) {
        this.visibleRange = Integer.parseInt(data[8]);
      }
    }
  }
  
  protected void loadFromConfig(ConfigurationSection config)
  {
    this.red = config.getInt("red", this.red);
    this.green = config.getInt("green", this.green);
    this.blue = config.getInt("blue", this.blue);
    this.xSpread = ((float)config.getDouble("horiz-spread", this.xSpread));
    this.ySpread = ((float)config.getDouble("vert-spread", this.ySpread));
    this.zSpread = ((float)config.getDouble("z-spread", this.zSpread));
    this.yOffset = ((float)config.getDouble("y-offset", this.yOffset));
    this.fOffset = ((float)config.getDouble("forward-offset", this.fOffset));
    this.sOffset = ((float)config.getDouble("side-offset", this.sOffset));
    this.speed = ((float)config.getDouble("speed", this.speed));
    this.visibleRange = config.getInt("render-distance", this.visibleRange);
    this.count = config.getInt("count", this.count);
  }
  
  public void playEffectLocation(Location location)
  {
    EffectLib lib = EffectLib.instance();
    EffectManager em = new EffectManager(lib);
    PlayEffect effect = new PlayEffect(em);
    effect.yOffset = yOffset;
    effect.particle = ParticleEffect.REDSTONE;
    effect.color = Color.fromBGR(this.blue, this.green, this.red);
    effect.iterations = 1;
    effect.setDynamicOrigin(new DynamicLocation(location));
    effect.particles = this.count;
    effect.xradius = this.xSpread;
    effect.yradius = this.ySpread;
    effect.visibleRange = this.visibleRange;
    
    effect.start();
  }
}
