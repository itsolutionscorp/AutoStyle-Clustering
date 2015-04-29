class Integer
  def to_roman
    IntegerToRoman.new(self).convert
  end
end

class IntegerToRoman
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def convert
    roman_units     = RomanUnit.new(number).convert
    roman_tens      = RomanTens.new(number).convert
    roman_hundreds  = RomanHundreds.new(number).convert
    roman_thousands = RomanThousands.new(number).convert

    return "#{roman_thousands}#{roman_hundreds}#{roman_tens}#{roman_units}"
  end
end

module RomanConverter
  attr_reader :number

  def initialize(number)
    @number = number.to_s[index].to_i if number.to_s[index]
  end

  def convert
    return if number.nil?

    case
    when number < 4
      one * number
    when number == 4
      "#{one}#{five}"
    when number == 5
      five
    when number <= 8
      "#{five}#{one * (number - 5)}"
    when number == 9
      "#{one}#{ten}"
    end
  end

  def index
    raise NotImplementedError
  end

  def one
    raise NotImplementedError
  end

  def five
    raise NotImplementedError
  end

  def ten
    raise NotImplementedError
  end
end

class RomanUnit
  include RomanConverter

  def index
    -1
  end

  def one
    'I'
  end

  def five
    'V'
  end

  def ten
    'X'
  end
end

class RomanTens
  include RomanConverter

  def index
    -2
  end

  def one
    'X'
  end

  def five
    'L'
  end

  def ten
    'C'
  end
end

class RomanHundreds
  include RomanConverter

  def index
    -3
  end

  def one
    'C'
  end

  def five
    'D'
  end

  def ten
    'M'
  end
end

class RomanThousands
  include RomanConverter

  def index
    -4
  end

  def one
    'M'
  end

  def five
    nil
  end

  def ten
    nil
  end
end
