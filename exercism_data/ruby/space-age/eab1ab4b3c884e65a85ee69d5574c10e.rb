class SpaceAge

  orbital = 31557600
  planet = {
    'on_earth' => 1,
    'on_mercury' => 0.2408467,
    'on_venus' => 0.61519726,
    'on_mars' => 1.8808158,
    'on_jupiter' => 11.862615,
    'on_saturn' => 29.447498,
    'on_uranus' => 84.016846,
    'on_neptune' => 164.79132,
  }

  def initialize(s)
    @s = s
  end

  def seconds
    @s
  end

  planet.each do |p, o|
    define_method(p) { (@s.to_f / ( orbital * o)).round(2) }
  end

end
