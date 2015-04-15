class SpaceAge
  attr_reader :seconds

  EARTH_DIVISOR = 31_557_600.0
  EARTH_REALTIVE_CONVERSIONS = {
    earth:   ->(earth_time) { earth_time },
    mercury: ->(earth_time) { earth_time / 0.2408467  },
    venus:   ->(earth_time) { earth_time / 0.61519726 },
    mars:    ->(earth_time) { earth_time / 1.8808158  },
    jupiter: ->(earth_time) { earth_time / 11.862615  },
    saturn:  ->(earth_time) { earth_time / 29.447498  },
    uranus:  ->(earth_time) { earth_time / 84.016846  },
    neptune: ->(earth_time) { earth_time / 164.79132  },
  }

  def initialize(seconds)
    @seconds = seconds
  end

  EARTH_REALTIVE_CONVERSIONS.each do |planet, proc|
    define_method "on_#{planet}" do
      proc.call(normalize_to_earth_years).round(2)
    end
  end

  private
  def normalize_to_earth_years
    seconds / EARTH_DIVISOR
  end
end
