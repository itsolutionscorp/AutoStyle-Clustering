module MyRoman
  @letters = {
    'M' => 1000,
    'D' => 500,
    'C' => 100,
    'L' => 50,
    'X' => 10,
    'V' => 5,
    'I' => 1
  }

  def self.to_roman(number)
    romanized_string = ""
    previous_letter = 'M'

    @letters.each do |letter, letter_value|
      letter_multiplier = number / letter_value
      if letter_multiplier > 3
        difference = 5 - letter_multiplier
        romanized_string << letter * difference
        romanized_string << previous_letter
      else
        romanized_string << letter * letter_multiplier
      end
      number -= letter_multiplier * letter_value
      previous_letter = letter
    end

    to_proper_roman(romanized_string)
  end

  def self.to_proper_roman(improper_roman)
    improper_roman.sub!(/DCD/, 'CM')
    improper_roman.sub!(/LXL/, 'XC')
    improper_roman.sub!(/VIV/, 'IX')
    improper_roman
  end
end

class Integer
  def to_roman
    MyRoman.to_roman(self)
  end
end
