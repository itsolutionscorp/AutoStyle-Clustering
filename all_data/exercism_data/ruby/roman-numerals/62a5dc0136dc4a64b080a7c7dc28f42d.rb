class Fixnum

  def to_roman
    to_return = ""
    numerals = {
      "1" => "I",
      "2" => "II",
      "3" => "III",
      "4" => "IV",
      "5" => "V",
      "6" => "VI",
      "7" => "VII",
      "8" => "VIII",
      "9" => "IX",
      "10" => "X",
      "20" => "XX",
      "30" => "XXX",
      "40" => "XL",
      "50" => "L",
      "60" => "LX",
      "70" => "LXX",
      "80" => "LXXX",
      "90" => "XC",
      "100" => "C",
    }
    get_by_units {|num| to_return << numerals[num] }
    to_return
  end

  private 

  def get_by_units
    num_array = self.to_s.chars.map(&:to_i)
    length = num_array.length
    num_array.each_with_index.map do |num, index| 
        zero_to_add = length - (index + 1)
        yield (num.to_s + ("0" * zero_to_add))
    end
  end
end
