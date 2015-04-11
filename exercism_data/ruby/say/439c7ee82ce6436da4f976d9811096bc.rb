class Say
  TRANSLATIONS = {
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
    13 => "thriteen",
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
    90 => "ninety",
  }

  def initialize(number)
    @number = number
    fail ArgumentError if invalid?
  end

  def in_english
    translate_nine_digits(@number)
  end

  def translate_nine_digits(number)
    first_digits = number / 10**9
    remaining = number % 10**9

    return translate_six_to_eight_digits(@number) if first_digits == 0

    prefix = translate_three_digits(first_digits) + " billion"

    return prefix if remaining == 0

    prefix + " " + translate_six_to_eight_digits(remaining)
  end

  def translate_six_to_eight_digits(number)
    first_digit = number / 10**6
    remaining = number % 10**6

    return translate_four_and_five_digits(number) if first_digit == 0

    prefix = translate_three_digits(first_digit) + " million"

    return prefix if remaining == 0

    prefix + " " + translate_four_and_five_digits(remaining)
  end

  def translate_four_and_five_digits(number)
    thousands = number / 1000
    remaining = number % 1000

    return translate_three_digits(number) if thousands == 0

    prefix = translate_three_digits(thousands) + " thousand"

    return prefix if remaining == 0

    prefix + " " + translate_three_digits(remaining)
  end

  def translate_three_digits(number)
    hundreds = number / 100
    remaining = number % 100
    return translate_two_digits(number) if hundreds == 0

    prefix = TRANSLATIONS[hundreds] + " hundred"

    return prefix if remaining == 0

    prefix + " " + translate_two_digits(remaining)
  end

  def translate_two_digits(number)
    tens = (number / 10) * 10
    units = number % 10

    return translate_one_digit(units) if tens == 0
    return "#{TRANSLATIONS[number]}" if units == 0
    return "#{TRANSLATIONS[number]}" unless between_20_and_100.call(number)

    "#{TRANSLATIONS[tens]}-#{translate_one_digit(units)}"
  end

  def translate_one_digit(number)
    TRANSLATIONS[number]
  end

  def between_20_and_100
    -> (x) { x > 20 && x < 100 }
  end

  def invalid?
    @number < 0 || @number >= 10**12
  end
end
