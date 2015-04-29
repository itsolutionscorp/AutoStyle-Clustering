class Raindrops
  def self.convert(number, converter: Converter.new)
    converter.convert(number.to_i) || number.to_s
  end
end


class Converter
  def initialize(conversions: Conversions.default)
    @conversions = conversions
  end

  def convert(number)
    conversions
      .select {|rule, _| applies?(rule, number) }
      .values
      .reduce(:+)
  end

  private

  attr_reader :conversions

  def applies?(rule, number)
    rule.call(number)
  end
end


module Conversions
  module_function

  def default
    {
      divisible_by(3) => 'Pling',
      divisible_by(5) => 'Plang',
      divisible_by(7) => 'Plong'
    }
  end

  def divisible_by(factor)
    ->(number) { number % factor == 0}
  end

end
