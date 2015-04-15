class Base10Component
  attr_accessor :roman_digits, :digit
  attr_accessor :bottom_digit, :middle_digit
  attr_accessor :top_digit

  MIDDLE_VALUE = 5

  RomanDigits = Struct.new(:bottom, :middle, :top)
  ROMAN_DIGITS = {1000 => RomanDigits.new('M', nil, nil),
                  100 => RomanDigits.new('C', 'D', 'M'),
                  10 => RomanDigits.new('X', 'L', 'C'),
                  1 => RomanDigits.new('I', 'V', 'X')}

  def initialize(digit, exponent)
    @digit = digit
    roman_digits = ROMAN_DIGITS[10 ** exponent]
    @bottom_digit = roman_digits.bottom
    @middle_digit = roman_digits.middle
    @top_digit = roman_digits.top
  end

  def to_roman
    case digit
      when 0 then ""
      when 1..3 then  bottom_digit * digit
      when 4 then bottom_digit + middle_digit
      when 5 then middle_digit
      when 6..8 then middle_digit + bottom_digit * (digit - MIDDLE_VALUE)
      when 9 then bottom_digit + top_digit
      when 10 then top_digit
    end
  end
end

module Base10
  #def base_10_components
  #  current_value = self
  #  base_10_magnitudes.reverse.each_with_object([]) do |exponent, base_10_components|
  #    magnitude = 10 ** exponent
  #    digit = current_value / magnitude
  #    base_10_components << Base10Component.new(digit, exponent)
  #    current_value = current_value % magnitude
  #  end
  #end
  #
  #def base_10_magnitudes
  #  (0...number_of_base_10_digits).to_a
  #end

  #def number_of_base_10_digits
  #  num_digits = self.to_s.length
  #end

  def base_10_components
    self.to_s.chars.reverse.each_with_index.map do |exponent, digit|
      Base10Component.new(digit, exponent)
    end
  end
end

module Romanizeable
  include Base10

  def to_roman
    base_10_components.map { |component| component.to_roman }.join
  end
end

class Integer
  include Romanizeable
end
