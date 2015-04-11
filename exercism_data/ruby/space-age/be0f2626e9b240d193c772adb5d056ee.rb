require 'bigdecimal'

class SpaceAge
  attr_reader :seconds

  CONVERSION_FACTORS = {
    :earth => 1,
    :mercury => 0.2408467,
    :venus => 0.61519726,
    :mars => 1.8808158,
    :jupiter => 11.862615,
    :saturn => 29.447498,
    :uranus => 84.016846,
    :neptune => 164.79132
  }

  def initialize(seconds)
    @seconds = BigDecimal.new(seconds)
  end

  def on_earth
    format(convert_from_earth_years(CONVERSION_FACTORS[:earth]))
  end

  def on_mercury
    format(convert_from_earth_years(CONVERSION_FACTORS[:mercury]))
  end

  def on_venus
    format(convert_from_earth_years(CONVERSION_FACTORS[:venus]))
  end

  def on_mars
    format(convert_from_earth_years(CONVERSION_FACTORS[:mars]))
  end

  def on_jupiter
    format(convert_from_earth_years(CONVERSION_FACTORS[:jupiter]))
  end

  def on_saturn
    format(convert_from_earth_years(CONVERSION_FACTORS[:saturn]))
  end

  def on_uranus
    format(convert_from_earth_years(CONVERSION_FACTORS[:uranus]))
  end

  def on_neptune
    format(convert_from_earth_years(CONVERSION_FACTORS[:neptune]))
  end

  private

  def format(num)
    num.round(2)
  end

  def convert_from_earth_years(factor = 1)
    seconds.to_f / ( 31557600 * factor )
  end
end
