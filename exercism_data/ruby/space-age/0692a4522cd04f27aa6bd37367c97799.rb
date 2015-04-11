require 'bigdecimal'

class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = BigDecimal.new(seconds)
  end

  {
    :earth => 1,
    :mercury => 0.2408467,
    :venus => 0.61519726,
    :mars => 1.8808158,
    :jupiter => 11.862615,
    :saturn => 29.447498,
    :uranus => 84.016846,
    :neptune => 164.79132
  }.each do |planet, conversion_factor|

    define_method("on_#{planet}") do
      format(convert_from_earth_years(conversion_factor))
    end

  end

  private

  def format(num)
    num.round(2)
  end

  def convert_from_earth_years(factor = 1)
    # this is an optimization to minimize floating point truncation error
    seconds.to_f / ( 31557600 * factor )
  end
end
