class Say
  TENS = {
    90 => 'ninety',
    80 => 'eighty',
    70 => 'seventy',
    60 => 'sixty',
    50 => 'fifty',
    40 => 'forty',
    30 => 'thirty',
    20 => 'twenty',
    19 => 'nineteen',
    18 => 'eighteen',
    17 => 'seventeen',
    16 => 'sixteen',
    15 => 'fifteen',
    14 => 'fourteen',
    13 => 'thirteen',
    12 => 'twelve',
    11 => 'eleven',
    10 => 'ten'
  }
  ONES = {
    9 => 'nine',
    8 => 'eight',
    7 => 'seven',
    6 => 'six',
    5 => 'five',
    4 => 'four',
    3 => 'three',
    2 => 'two',
    1 => 'one'
  }

  def initialize(number)
    @number = number
  end
  attr_reader :number

  def in_english
    raise ArgumentError if number < 0 || number > 999_999_999_999
    return 'zero' if number == 0
    system "say", string_builder(self.counter)
    string_builder(self.counter)
  end

  def two_digit(number)
    count_number = number
    ans = ''
    TENS.each do |numeric, english|
      if count_number >= numeric
        ans += english
        count_number -= numeric
      end
    end

    ONES.each do |numeric, english|
      if count_number >= numeric
        ans += "-#{english}"
        count_number -= numeric
      end
    end
  ans[0] == "-" ? ans[1..-1] : ans
  end

  def three_digit(number)
    return "one hundred" if number == 100
    ans = "#{two_digit((number / 100))} hundred #{two_digit((number % 100))}"
    ans.strip
  end

  def digit_check(number)
    if number < 100
      two_digit(number)
    else
      three_digit(number)
    end
  end

  def counter
    places = {
      1_000_000_000 => 0,
          1_000_000 => 0,
              1_000 => 0,
    }
    count_number = number
    places.each do |num, _value|
      if num <= count_number
        places[num] += (count_number / num)
        count_number = count_number % num
      end
    end
    places
  end

  def string_builder(array)
    ans = ''
    count_number = number
    if array[1_000_000_000] > 0
      ans += "#{digit_check(array[1_000_000_000])} billion "
      count_number = count_number % 1_000_000_000
    end

    if array[1_000_000] > 0
      ans += "#{digit_check(array[1_000_000])} million "
      count_number = count_number % 1_000_000
    end

    if array[1_000] > 0
      ans += "#{digit_check(array[1_000])} thousand "
      count_number = count_number % 1_000
    end

    ans += "#{digit_check(count_number)}"
  ans.strip
  end
end
