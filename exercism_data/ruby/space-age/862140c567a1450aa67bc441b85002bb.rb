class SpaceAge
  attr_accessor :seconds

  def initialize (seconds)
     @seconds= seconds
   end

  def seconds
    @seconds
  end

  def on_earth
    (@seconds * 3.16877086433912e-08 ).round(2)
  end

   def on_mercury
     (@seconds * 1.3156984473270618e-07).round(2)
   end

  def on_venus
    (@seconds * 5.151711150867197e-08).round(2)
  end

  def on_mars
    (@seconds * 1.6846424533248636e-08).round(2)
  end

  def on_jupiter
    (@seconds *  2.6722065774198312e-09).round(2)
  end

   def on_saturn
     (@seconds * 1.0766666666666667e-09).round(2)
   end

  def on_uranus
    (@seconds * 3.769325437432647e-10).round(2)
  end

  def on_neptune
    (@seconds *  1.9244534000829524e-10).round(2)
  end

end
