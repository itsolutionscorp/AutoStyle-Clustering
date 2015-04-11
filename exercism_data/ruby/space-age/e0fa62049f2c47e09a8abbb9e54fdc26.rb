class SpaceAge

  attr_reader :seconds

  def initialize age_in_seconds
    @seconds = (age_in_seconds).to_f
    @earth_orbital_seconds = 31557600
  end

  PLANET_ORBITAL_PERIODS = {
                          earth: 1,
                          mercury: 0.2408467, 
                          venus: 0.61519726,
                          mars: 1.8808158,
                          jupiter: 11.862615,
                          saturn: 29.447498,
                          uranus: 84.016846,
                          neptune: 164.79132 }
  
  PLANET_ORBITAL_PERIODS.each do |planet, period|
    define_method("on_#{planet}") do
      (@seconds / (period * @earth_orbital_seconds)).round(2)
    end
  end

end
