#!/usr/bin/env ruby

# Exercism 6
# Roman Numerals Conversion
# Convert numbers to Roman Numerals

# I-V-X-L-C-D-M
# 1-5-10-50-100-500-1000

module RomanNumeral

  def to_roman

    romans = { 'I' => 1,
               'V' => 5,
               'X' => 10,
               'L' => 50,
               'C' => 100,
               'D' => 500,
               'M' => 1000 }

    romans_reduce = { 
                      'IIII' => 'IV', 
                      'XXXX' => 'XL',
                      'CCCC' => 'CD',
                      'VIIII' => 'IX',
                      'XIIII' => 'IL',
                      'LIIII' => 'IC',
                      'CIIII' => 'ID',
                      'DIIII' => 'IM',
                      'DCCCC' => 'CM',
                      'LXL' => 'XC',
                      'VIV' => 'IX',
                      'DCD' => 'CM' }


    converted = ''
    (self / 1000).times { |x| converted << romans.invert[1000] }
    (self % 1000 / 500).times { |x| converted << romans.invert[500] }
    (self % 1000 % 500 / 100).times { |x| converted << romans.invert[100] }
    (self % 1000 % 500 % 100 / 50).times { |x| converted << romans.invert[50] }
    (self % 1000 % 500 % 100 % 50 / 10).times { |x| converted << romans.invert[10] }
    (self % 1000 % 500 % 100 % 50 % 10 / 5).times { |x| converted << romans.invert[5] }
    (self % 1000 % 500 % 100 % 50 % 10 % 5 / 1).times { |x| converted << romans.invert[1] }

    romans_reduce.keys.each { |x| converted.gsub!(x, romans_reduce) }

    converted

  end

end

class Integer
  include RomanNumeral
end

class Array
  def gsub(pattern, replacement)
    each { |x|
      x.gsub!(pattern, replacement)
      }
  end
end
