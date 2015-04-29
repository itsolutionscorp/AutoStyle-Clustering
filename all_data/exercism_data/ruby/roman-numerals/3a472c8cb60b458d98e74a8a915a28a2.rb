class Fixnum
  @@zero = {
    0 => nil
  }

  @@unit = {
    1 => "I",
    2 => "II",
    3 => "III",
    4 => "IV",
    5 => "V",
    6 => "VI",
    7 => "VII",
    8 => "VIII",
    9 => "IX"
  }.merge(@@zero)

  @@ten = {
    1 => "X",
    2 => "XX",
    3 => "XXX",
    4 => "XL",
    5 => "L",
    6 => "LX",
    7 => "LXX",
    8 => "LXXX",
    9 => "XC"
  }.merge(@@zero)

  @@hundred = {
    1 => "C",
    2 => "CC",
    3 => "CCC",
    4 => "CD",
    5 => "D",
    6 => "DC",
    7 => "DCC",
    8 => "DCCC",
    9 => "CM"
  }.merge(@@zero)

  @@thousand = {
    1 => "M",
    2 => "MM",
    3 => "MMM"
  }.merge(@@zero)

  def to_roman
    array = self.to_s.split(//).map { |n| n.to_i }
    digits = array.count
    roman = []

    if digits == 1
      roman << @@unit[array[digits - 1]]
      return roman.compact.inject(:+)
    end

    roman << @@thousand[array[digits - 4]] if digits >= 4
    roman << @@hundred[array[digits - 3]] if digits >= 3
    roman << @@ten[array[digits - 2]] if digits >= 2
    roman << @@unit[array[digits - 1]] if digits > 1

    roman.compact.inject(:+)
  end

end
