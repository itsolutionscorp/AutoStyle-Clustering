class SpaceAge
  attr_reader :seconds, :on_earth, :on_mercury, :on_venus, :on_mars, :on_jupiter, :on_saturn, :on_uranus, :on_neptune
  def initialize (seconds)
    @seconds = seconds
    @on_earth = (@seconds/31557600.to_f).round(2)
    @on_mercury = (@on_earth/0.2408467).round(2)
    @on_venus = (@on_earth/0.61519726).round(2)
    @on_mars = (@on_earth/1.8808158).round(2)
    @on_jupiter = (@on_earth/11.862615).round(2)
    @on_saturn = (@on_earth/29.447498).round(2)
    @on_uranus = (@on_earth/84.016846).round(2)
    @on_neptune = (@on_earth/164.79132).round(2)
  end
end
