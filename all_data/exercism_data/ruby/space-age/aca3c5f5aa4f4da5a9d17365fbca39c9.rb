class SpaceAge

  EARTH_YEAR = 31557600.0  #seconds per year

  # orbital periods in earth years
  PLANET_PERIODS = {
   :earth =>   1.00,
   :mercury =>   0.2408467,
   :venus =>   0.61519726,
   :mars =>   1.8808158,
   :jupiter =>   11.862615,
   :saturn =>   29.447498,
   :uranus =>   84.016846,
   :neptune =>   164.79132
  }

  def initialize(period)
    @period = period
  end

  def seconds
    @period
  end

  PLANET_PERIODS.keys.each do |k|
    define_method("on_#{k.to_s}") do
      ((@period / EARTH_YEAR) / PLANET_PERIODS[k]).round(2)
    end
  end
end
