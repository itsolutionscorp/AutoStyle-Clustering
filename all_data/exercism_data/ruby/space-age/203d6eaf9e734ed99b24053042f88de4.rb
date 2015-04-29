class SpaceAge
  class Planet
    ORBITAL_PERIODS = {
      mercury: 0.2408467,
      venus:   0.61519726,
      earth:   1.0,
      mars:    1.8808158,
      jupiter: 11.862615,
      saturn:  29.447498,
      uranus:  84.016846,
      neptune: 164.79132,
    }

    SECONDS_PER_EARTH_YEAR = 365.25 * 24 * 60 * 60

    def initialize(key)
      @key = key
    end

    def years(seconds)
      (seconds / seconds_per_year).round(2)
    end

    private

    def seconds_per_year
      SECONDS_PER_EARTH_YEAR * orbital_period
    end

    def orbital_period
      ORBITAL_PERIODS.fetch(@key)
    end
  end

  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  Planet::ORBITAL_PERIODS.keys.each do |planet|
    define_method("on_#{planet}") do     # def on_earth
      Planet.new(planet).years(seconds)  #   Planet.new(:earth).years(seconds)
    end                                  # end
  end
end
