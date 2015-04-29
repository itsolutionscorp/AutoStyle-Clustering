class Integer
  def to_roman
    IntegerToRoman.convert(self)
  end
end

class IntegerToRoman
  def self.convert(number)
    new(number).to_roman
  end

  def initialize(number)
    @number = number
  end

  attr_reader :number

  SYMBOLS_MAP = {
    'M'  => 1000,
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
    'I'  => 1
  }

  def to_roman
    current = number

    SYMBOLS_MAP.each_with_object("") do |(symbol, value), result|
      if (times = current / value) > 0
        result << symbol * times
        current -= value * times
      end
    end
  end
end
