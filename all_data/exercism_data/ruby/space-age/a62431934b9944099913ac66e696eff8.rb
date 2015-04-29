class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def self.factors
    {
      mercury: 0.2408467,
      venus:   0.61519726,
      earth:   1.0,
      mars:    1.8808158,
      jupiter: 11.862615,
      saturn:  29.447498,
      uranus:  84.016846,
      neptune: 164.79132
    }
  end

  factors.each do |planet, factor|
    define_method "on_#{ planet }" do
      (years / factor).round(2)
    end
  end

  private

  def years
    seconds / (365.25 * 24 * 60 * 60)
  end
end
