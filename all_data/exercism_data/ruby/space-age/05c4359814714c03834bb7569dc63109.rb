class SpaceAge
  attr_reader :seconds
  def initialize seconds
    @seconds = seconds
  end

  private

  def method_missing method, *args, &block
    in_years_of extract_planet(method) or super
  end

  def respond_to? method
    PLANET_YEAR.include? extract_planet(method) or super
  end

  def extract_planet method
    $1.to_sym if method =~ /\Aon_(\w+)\z/
  end

  def in_years_of planet
    year = PLANET_YEAR[planet] * SECONDS
    (seconds / year).round(2) if year
  end

  PLANET_YEAR = {
   earth: 1.0,
   mercury: 0.2408467,
   venus: 0.61519726,
   mars: 1.8808158,
   jupiter: 11.862615,
   saturn: 29.447498,
   uranus: 84.016846,
   neptune: 164.79132,
  }
  SECONDS = 31557600
end
