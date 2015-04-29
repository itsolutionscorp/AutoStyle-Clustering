class Fixnum

  def to_roman
    roman_number = Roman.new(self.abs)
    roman_number.to_roman
  end

end

class Roman

  @@roman_values = {M: 1000, CM: 900, D: 500, CD: 400, C: 100, XC: 90, L: 50, XL: 40, X: 10, IX: 9, V: 5, IV: 4, I: 1 }

  def initialize num
    @decimal_value = num
  end

  public

  def to_roman
    rest = @decimal_value
    output_string = ""
    @@roman_values.each do |symbol, value|
        (rest / value).times { output_string << symbol.to_s }
        rest %= value
    end
    output_string
  end

end
