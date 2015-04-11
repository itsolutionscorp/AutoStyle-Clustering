class Say
  def initialize(value)
    @value = value
  end

  def in_english
    fail ArgumentError if @value < 0
    fail ArgumentError if @value > 999_999_999_999

    text_fragments = []

    text_fragments << print_billions(@value)
    text_fragments << print_millions(@value)
    text_fragments << print_thousands(@value)
    text_fragments << print_three_digit_number(@value)

    text = join_non_empty(text_fragments)

    text != '' ? text : 'zero'
  end

  private

  def print_billions(n)
    count_text = print_three_digit_number((n / 1_000_000_000) % 1_000_000_000)
    print_count(count_text, 'billion')
  end

  def print_millions(n)
    count_text = print_three_digit_number((n / 1_000_000) % 1_000_000)
    print_count(count_text, 'million')
  end

  def print_thousands(n)
    count_text = print_three_digit_number((n / 1_000) % 1_000)
    print_count(count_text, 'thousand')
  end

  def print_three_digit_number(n)
    three_digit_number = n % 1_000
    join_non_empty([
      print_hundreds(three_digit_number / 100),
      print_two_digit_number(three_digit_number % 100)
    ])
  end

  def print_hundreds(h)
    print_count(UNITS[h], 'hundred')
  end

  def print_two_digit_number(n)
    case n
    when 11..19 then TEENS[n]
    else
      tens = TENS[n / 10]
      units = UNITS[n % 10]
      join_non_empty([tens, units], '-')
    end
  end

  def print_count(count_text, unit_text)
    return if count_text.to_s.empty?
    join_non_empty([count_text, unit_text])
  end

  def join_non_empty(array, delimiter = ' ')
    array.select { |x| x.to_s != '' }.join(delimiter)
  end

  UNITS = {
    1 => 'one',
    2 => 'two',
    3 => 'three',
    4 => 'four',
    5 => 'five',
    6 => 'six',
    7 => 'seven',
    8 => 'eight',
    9 => 'nine'
  }
  TEENS = {
    11 => 'eleven',
    12 => 'twelve',
    13 => 'thirteen',
    14 => 'fourteen',
    15 => 'fifteen',
    16 => 'sixteen',
    17 => 'seventeen',
    18 => 'eighteen',
    19 => 'nineteen'
  }
  TENS = {
    1 => 'ten',
    2 => 'twenty',
    3 => 'thirty',
    4 => 'forty',
    5 => 'fifty',
    6 => 'sixty',
    7 => 'seventy',
    8 => 'eighty',
    9 => 'ninety'
  }
end
