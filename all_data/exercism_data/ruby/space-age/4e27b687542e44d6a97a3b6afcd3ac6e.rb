class SpaceAge < Struct.new(:seconds)

  YEAR_LENGTH = {
    :mercury => 0.2408467,
    :venus   => 0.61519726,
    :earth   => 1.0,
    :mars    => 1.8808158,
    :jupiter => 11.862615,
    :saturn  => 29.447498,
    :uranus  => 84.016846,
    :neptune => 164.79132,
  }

  def self.planets
    YEAR_LENGTH.keys
  end

  # enables Pluto to be added back in with a single line
  # Viva la Pluto!
  planets.each do |planet|
    define_method "on_#{planet}" do
      (seconds/year_length_in_seconds_for(planet)).round(2)
    end
  end

  def year_length_in_seconds_for(planet)
    YEAR_LENGTH[planet] * 31557600
  end
end
