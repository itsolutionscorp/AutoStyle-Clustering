class Fixnum
  def to_roman
    roman_units     = RomanUnitConverter.new(self).convert
    roman_tens      = RomanTensConverter.new(self).convert
    roman_hundreds  = RomanHundredsConverter.new(self).convert
    roman_thousands = RomanThousandsConverter.new(self).convert

    return "#{roman_thousands}#{roman_hundreds}#{roman_tens}#{roman_units}"
  end

  private

  def roman_for(number, one, five, ten)
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

  def roman_hundreds_for(number)
    roman_for(number, 'C', 'D', 'M')
  end

  def roman_thousands_for(number)
    roman_for(number, 'M', nil, nil)
  end
end

class RomanConverter
  attr_reader :number

  def initialize(number)
    @number = number
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

class RomanUnitConverter < RomanConverter
  def initialize(number)
    @number = number.to_s[-1].to_i if number.to_s[-1]
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

class RomanTensConverter < RomanConverter
  def initialize(number)
    @number = number.to_s[-2].to_i if number.to_s[-2]
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

class RomanHundredsConverter < RomanConverter
  def initialize(number)
    @number = number.to_s[-3].to_i if number.to_s[-3]
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

class RomanThousandsConverter < RomanConverter
  def initialize(number)
    @number = number.to_s[-4].to_i if number.to_s[-4]
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
