class Say
  attr_reader :number

  ONES = {
      1 => 'one',
      2 => 'two',
      3 => 'three',
      4 => 'four',
      5 => 'five',
      6 => 'six',
      7 => 'seven',
      8 => 'eight',
      9 => 'nine',
      10 => 'ten',
      11 => 'eleven',
      12 => 'twelve',
      13 => 'thirteen',
      14 => 'fourteen',
      15 => 'fifteen',
  }

  TENS = {
      20 => 'twenty',
      30 => 'thirty',
      40 => 'forty',
      50 => 'fifty',
      60 => 'sixty',
      70 => 'seventy',
      80 => 'eighty',
      90 => 'ninty',
  }

  def initialize(number)
    @number = number
  end

  def in_english
    to_word(number)
  end

  private

  def to_word(n)
    case n
    when 0 then handle_zero(n)
    when 1...16 then handle_ones(n)
    when 16...20 then handle_tens(n)
    when 20...100 then handle_teens(n)
    when 100...1_000 then handle_hundreds(n)
    when 1_000...1_000_000 then handle_thousands(n)
    when 1_000_000...1_000_000_000 then handle_millions(n)
    when 1_000_000_000...1_000_000_000_000 then handle_billions(n)
    else fail ArgumentError, "ArgumentError #{n} for (0...1_000_000_000_000_000)"
    end
  end

  def handle_zero(n)
    'zero'
  end

  def handle_ones(n)
    ONES[n]
  end

  def handle_tens(n)
   ONES[n - 10] + 'teen'
  end

  def handle_teens(n)
    factor, remainder = n.divmod(10)
    TENS[factor * 10] + (remainder.zero? ? '' : "-#{ONES[remainder]}")
  end

  def handle_hundreds(n)
    factor, remainder = n.divmod(100)
    to_word(factor) + ' hundred' + (remainder.zero? ? '' : " #{to_word(remainder)}")
  end

  def handle_thousands(n)
    factor, remainder = n.divmod(1000)
    to_word(factor) + ' thousand' + (remainder.zero? ? '' : " #{to_word(remainder)}")
  end

  def handle_millions(n)
    factor, remainder = n.divmod(1_000_000)
    to_word(factor) + ' million' + (remainder.zero? ? '' : " #{to_word(remainder)}")
  end

  def handle_billions(n)
    factor, remainder = n.divmod(1_000_000_000)
    to_word(factor) + ' billion' + (remainder.zero? ? '' : " #{to_word(remainder)}")
  end
end
