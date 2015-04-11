class Fixnum
  def to_roman
    dictionary = {1000 => "M", 500 => "D", 100 => "C", 50 => "L", 10 => "X", 5 => "V", 1 => "I"}
    RomanNumerals.new(dictionary, self).to_s
  end
end

class RomanNumerals
  attr_accessor :dictionary, :number
  def initialize(dictionary, number)
    self.dictionary = dictionary
    self.number = number
  end

  def to_s
    letters = ""
    num = number
    dictionary.keys.each_slice(2) do |multiple, _|
      l, num = convert_to_letters(num, multiple, dictionary)
      letters += l
    end

    letters
  end

  def convert_to_letters(num, multiple, dictionary)
    x = num / multiple
    rom = case
    when (x == 9)
      "#{dictionary[multiple]}#{dictionary[multiple * 10]}"
    when (x >= 5)
      dictionary[multiple * 5] + (dictionary[multiple] * (x - 5))
    when (x == 4)
      "#{dictionary[multiple]}#{dictionary[multiple * 5]}"
    else
      dictionary[multiple] * x
    end
    [rom, num.remainder(multiple)]
  end
end
