class Roman
  UNITS_TO_VALUES = {
    "M" => 1000,
    "D" => 500,
    "C" => 100,
    "L" => 50,
    "X" => 10,
    "V" => 5,
    "I" => 1
  }
  UNITS = UNITS_TO_VALUES.keys

  def self.upper_unit_of(roman_unit)
    UNITS[UNITS.index(roman_unit) - 1]
  end
end

class String
  def normalise_romans
    match = self.match(/([#{Roman::UNITS.join}])\1{3}/) # any same four units could be "unit" + the upper unit
    self.sub!(/([#{Roman::UNITS.join}])\1{3}/, match[1] + Roman.upper_unit_of(match[1])) if match
    match = self.match(/([#{Roman::UNITS.join}])(\w)\1/) # any "DCD" pattern could be "CM" as two "D" is "M"
    return self unless match && match[1] != match[2]

    upper_unit, lower_unit = Roman.upper_unit_of(match[1]), match[2]
    self.sub!(/([#{Roman::UNITS.join}])\w\1/, "#{lower_unit}#{upper_unit}")
  end
end

class Fixnum
  def calculate_roman
    current = self
    Roman::UNITS_TO_VALUES.inject("") do |roman_numerals, (roman_unit, value)|
      quotient, remain = current.divmod(value)
      current = remain
      roman_numerals << roman_unit * quotient
    end
  end

  def to_roman
    self.calculate_roman.normalise_romans
  end
end
