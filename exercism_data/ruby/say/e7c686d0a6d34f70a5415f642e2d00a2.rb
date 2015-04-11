# say.rb
class Say
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def in_english
    fail ArgumentError if number < 0 || number > UPPER_BOUND
    return number_names[0] if number.zero?
    convert(number).join
  end

  private

  def convert(remainder, index = 0)
    return [] if remainder.zero?
    divisor = number_names.keys[index]
    quotient, remainder = remainder.divmod(divisor)
    tail = convert(remainder, index + 1)
    return tail if quotient.zero?
    convert_quotient(quotient, divisor) +
      convert_divisor(divisor, remainder) + tail
  end

  def convert_quotient(quotient, divisor)
    return [] if divisor < 100
    convert(quotient) << space
  end

  def convert_divisor(divisor, remainder)
    [number_names[divisor], divisor_separator(divisor, remainder)]
  end

  def divisor_separator(divisor, remainder)
    return if remainder.zero?
    if hyphenate?(divisor, remainder)
      hyphen
    else
      space
    end
  end

  def hyphenate?(divisor, remainder)
    divisor < 100 && remainder < 10
  end

  def hyphen
    '-'
  end

  def space
    ' '
  end

  def number_names
    NUMBER_NAMES
  end

  UPPER_BOUND = 10**12 - 1

  NUMBER_NAMES = {
    1_000_000_000 => 'billion',
    1_000_000     => 'million',
    1_000         => 'thousand',
    100           => 'hundred',
    90            => 'ninety',
    80            => 'eighty',
    70            => 'seventy',
    60            => 'sixty',
    50            => 'fifty',
    40            => 'forty',
    30            => 'thirty',
    20            => 'twenty',
    19            => 'nineteen',
    18            => 'eighteen',
    17            => 'seventeen',
    16            => 'sixteen',
    15            => 'fifteen',
    14            => 'fourteen',
    13            => 'thirteen',
    12            => 'twelve',
    11            => 'eleven',
    10            => 'ten',
    9             => 'nine',
    8             => 'eight',
    7             => 'seven',
    6             => 'six',
    5             => 'five',
    4             => 'four',
    3             => 'three',
    2             => 'two',
    1             => 'one',
    0             => 'zero'
  }
end
