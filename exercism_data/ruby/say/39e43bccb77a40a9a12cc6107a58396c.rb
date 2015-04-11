class Say
  STRAIGHT_TRANSLATION = {
    0 => "zero", 1 => "one", 2 => "two", 3 => "three",
    4 => "four", 5 => "five", 6 => "six", 7 => "seven",
    8 => "eight", 9 => "nine", 10 => "ten", 11 => "eleven",
    12 => "twelve"
  }

  BASE_TRANSLATION = {
    0 => "", 1 => "one", 2 => "twen", 3 => "thir",
    4 => "for", 5 => "fif", 6 => "six", 7 => "seven",
    8 => "eigh", 9 => "nine"
  }

  BIG_TRANSLATION = {
    100 => "hundred", 1000 => "thousand",
    1_000_000 => "million", 1_000_000_000 => "billion"
  }

  def initialize(number)
    @number = number
  end

  def in_english(number = @number)
    if (0..99).member? number
      zero_to_99 number
    elsif (100..999_999_999_999).member? number
      converter = BIG_TRANSLATION.keys.sort.reverse.find{ |n| number / n > 0 }
      after = number % converter == 0 ? "" : " " + in_english(number % converter)
      in_english(number / converter) + " #{BIG_TRANSLATION[converter]}" + after
    else
      raise ArgumentError
    end
  end

  def zero_to_99(number)
    if (0..12).member? number
      STRAIGHT_TRANSLATION[number]
    elsif number == 14
      "fourteen"
    elsif (13..19).member? number
      BASE_TRANSLATION[number % 10] + "teen"
    elsif (20..99).member? number
      after = number % 10 == 0 ? "" : "-" + in_english(number % 10)
      BASE_TRANSLATION[number / 10] + "ty" + after
    end
  end
end
