#!/usr/bin/env ruby

# Exercism 6
# Roman Numerals Conversion
# Convert numbers to Roman Numerals

# I-V-X-L-C-D-M
# 1-5-10-50-100-500-1000

module RomanNumeral

  def to_roman (roman = '')

  int = self

  roman_dict = { 'M'  => 1000,
                 'CM' => 900,
                 'D'  => 500,
                 'CD' => 400,
                 'C'  => 100,
                 'XC' => 90,
                 'L'  => 50,
                 'XL' => 40,
                 'X'  => 10,
                 'IX' => 9,
                 'V'  => 5,
                 'IV' => 4,
                 'I'  => 1 }

      roman_dict.map { |letter, value| roman << letter * (int/value); int = int % value }
      roman

  end

end

class Integer
  include RomanNumeral
end
