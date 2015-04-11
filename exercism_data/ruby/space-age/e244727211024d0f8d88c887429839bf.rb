class SpaceAge

  attr_reader :seconds

  RATIOS = {
    earth:   {orbital_years: 1, orbital_days: 365.25},
    mercury: {orbital_years: 0.2408467},
    venus:   {orbital_years: 0.61519726},
    mars:    {orbital_years: 1.8808158},
    jupiter: {orbital_years: 11.862615},
    saturn:  {orbital_years: 29.447498},
    uranus:  {orbital_years: 84.016846},
    neptune: {orbital_years: 164.79132}
  }

  RATIOS.keys.each do |planet|
    method_name = "on_#{planet}".to_sym
    define_method(method_name) {on(planet)}
  end

  def initialize(seconds)
    @seconds = seconds
  end

  def on(planet)
    (@seconds.to_i / (RATIOS[:earth][:orbital_days] *60*60*24 * RATIOS[planet][:orbital_years])).round(2)
  end

end
