class Fixnum
  def to_roman
    Roman.to_roman(self)
  end

  class Roman
    SYMBOLS = {
      1 => "I",
      4 => "IV",
      5 => "V",
      9 => "IX",
      10 => "X",
      40 => "XL",
      50 => "L",
      90 => "XC",
      100 => "C",
      400 => "CD",
      500 => "D",
      900 => "CM",
      1000 => "M"
    }

    def self.to_roman(number)
      if number <= 3 
        "I" * number
      else
        value, symbol = symbol_grater_than(number)
        symbol * (number/value) + to_roman(number % value)
      end
    end

    def self.symbol_grater_than(number)
      SYMBOLS
      .to_a
      .select { |value, symbol| number >= value }
      .last
    end
  end
end
