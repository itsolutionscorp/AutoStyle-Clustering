class SpaceAge
  CONVERSIONS = { mercury: 0.2408467, venus: 0.61519726, mars: 1.8808158, jupiter: 11.862615,
                  saturn:  29.447498, uranus:  84.016846, neptune: 164.79132, earth: 1.0 }

  attr_reader :seconds
  def initialize(seconds)
    @seconds = seconds
  end

  CONVERSIONS.each_key do |planet|
    define_method "on_#{planet}" do
      round(seconds / CONVERSIONS[planet] / 31557600.0)
    end
  end

  private

  def round(num)
    (num * 100).round.to_i / 100.0
  end
end
