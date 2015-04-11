# roman.rb
# author: Ray Wach
# date: 2015-01-20

class Fixnum
  def to_roman()
    @@ROMAN_NUMERALS ||= { 1000 => "M", 900 => "CM",
                           500 => "D", 400 => "CD",
                           100 => "C", 90 => "XC",
                           50 => "L", 40 => "XL",
                           10 => "X", 9 => "IX",
                           5 => "V", 4 => "IV",
                           1 => "I",
                         }

    i = self
    return @@ROMAN_NUMERALS.keys.each_with_object("") do |v, str|
      while ((i - v) >= 0)
        str << @@ROMAN_NUMERALS[v]
        i -= v
      end
    end
  end
end
