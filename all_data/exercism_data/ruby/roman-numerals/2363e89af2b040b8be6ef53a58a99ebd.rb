class Fixnum

  ROMANS = {
    1 => "I",
    5 => "V",
    10 => "X",
    50 => "L",
    100 => "C",
    500 => "D",
    1000 => "M"
  }

  def to_roman
    num_string = ""
    
    number = self

    [1000, 500, 100, 50, 10, 5, 1].each do |place|
      (number/place).times {num_string += ROMANS[place]}
      number = number % place
    end

    num_string.gsub!(/DCCCC/, "CM")
    num_string.gsub!(/CCCC/, "CD")
    num_string.gsub!(/LXXXX/, "XC")
    num_string.gsub!(/XXXX/, "XL")
    num_string.gsub!(/VIIII/, "IX")
    num_string.gsub!(/IIII/, "IV")
    num_string
  end

end
