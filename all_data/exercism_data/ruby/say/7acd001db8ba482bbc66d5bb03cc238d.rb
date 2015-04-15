class Say
  IN_A_WORD = {
    0 => "zero",
    1 => "one",
    2 => "two",
    3 => "three",
    4 => "four",
    5 => "five",
    6 => "six",
    7 => "seven",
    8 => "eight",
    9 => "nine",
    10 => "ten",
    11 => "eleven",
    12 => "twelve",
    13 => "thirteen",
    14 => "fourteen",
    15 => "fifteen",
    16 => "sixteen",
    17 => "seventeen",
    18 => "eighteen",
    19 => "nineteen",
    20 => "twenty",
    30 => "thirty",
    40 => "forty",
    50 => "fifty",
    60 => "sixty",
    70 => "seventy",
    80 => "eighty",
    90 => "ninety"
  }
  POWER_WORD = {
    2 => "hundred",
    3 => "thousand",
    6 => "million",
    9 => "billion"
  }

  def initialize(number)
    raise ArgumentError unless (0...10**12).include?(number)
    @number = number
  end

  def in_english
    in_words(@number)
  end

  private

  def in_words(n)
    IN_A_WORD[n] || in_many_words(n)
  end

  def in_many_words(n)
    power = power_of_ten(n)
    multiple = n / (10**power)
    case power
    when 1
      IN_A_WORD[multiple * 10] + "-" + IN_A_WORD[n.remainder(10)]
    else
      words = in_words(multiple) + " " + (POWER_WORD[power] || "blob(#{power})")
      remainder = n.remainder(10**power)
      words << " " << in_words(remainder) unless remainder.zero?
      words
    end
  end

  def power_of_ten(n)
    Math.log10(n).floor
  end
end
