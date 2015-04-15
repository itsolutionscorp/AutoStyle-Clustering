class SpaceAge

    { :on_earth   => 1.0,
      :on_mercury => 0.2408467,
      :on_venus   => 0.61519726,
      :on_mars    => 1.8808158,
      :on_jupiter => 11.862615,
      :on_saturn  => 29.447498,
      :on_uranus  => 84.016846,
      :on_neptune => 164.79132 }.each_pair do |name, orbit|
        define_method(name) { @age_on_earth.fdiv(orbit).round 2 }
      end
 
  def initialize age_in_seconds
    @age_in_seconds = age_in_seconds
    @age_on_earth   = age_in_seconds.fdiv 31557600
  end

  def seconds
    @age_in_seconds
  end

end
