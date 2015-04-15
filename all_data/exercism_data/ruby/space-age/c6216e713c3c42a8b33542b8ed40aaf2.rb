class SpaceAge
  PLANETS_YEARLY_ORBITAL_PERIOD_IN_EARTH_SECONDS = {earth: 31557600.0, mercury: 7600543.81992, venus: 19414149.052176, mars: 59354032.69008, jupiter: 374355659.12399995, saturn: 929292362.8848001, uranus: 2651370019.3296, neptune: 5200418560.032001}

  def initialize(age_in_seconds)
    @age_in_seconds = age_in_seconds
  end

  def seconds
    @age_in_seconds
  end

  PLANETS_YEARLY_ORBITAL_PERIOD_IN_EARTH_SECONDS.each do |planet, orbit_period|
    define_method("on_#{planet}") do
      (@age_in_seconds / orbit_period).round(2)    
    end
  end

end
