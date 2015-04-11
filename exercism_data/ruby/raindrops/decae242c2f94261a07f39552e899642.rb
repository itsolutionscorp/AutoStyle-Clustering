module Raindrops

  CONVERSIONS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    conversions = CONVERSIONS.map { |test, conversion| conversion if (number % test).zero? }.compact
    conversions.empty? ? number.to_s : conversions.join
  end
end
