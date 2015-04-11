class Say
  def initialize(n)
    raise ArgumentError, "Out of bounds" unless (0..999999999999).include?(n)
    @n = n
  end

  attr_reader :n

  def in_english
    words.compact.tap do |ary|
      ary.pop if ary.last == "zero" && ary.length > 1
    end.join(" ")
  end

  private

  NUMBERS = {
    10 ** 9 => "billion",
    10 ** 6 => "million",
    1000 => "thousand",
    100 => "hundred",
    90 => "ninety",
    80 => "eighty",
    70 => "seventy",
    60 => "sixty",
    50 => "fifty",
    40 => "forty",
    30 => "thirty",
    20 => "twenty",
    19 => "nineteen",
    18 => "eighteen",
    17 => "seventeen",
    16 => "sixteen",
    15 => "fifteen",
    14 => "fourteen",
    13 => "thirteen",
    12 => "twelve",
    11 => "eleven",
    10 => "ten",
    9  => "nine",
    8  => "eight",
    7  => "seven",
    6  => "six",
    5  => "five",
    4  => "four",
    3  => "three",
    2  => "two",
    1  => "one",
    0  => "zero"
  }

  def with_partial(t)
    yield t
  end

  def hundreds
    with_partial((n % 1000) / 100) {|t| "#{fetch(t)} hundred" if t > 0 }
  end

  def fetch(x, &block)
    NUMBERS.fetch(x, &block)
  end

  def compound_place(base)
    with_partial((n % (base * 1000)) / base) do |t|
      "#{Say.new(t).in_english} #{fetch(base)}" if t > 0
    end
  end

  def thousands
    compound_place(1000)
  end

  def millions
    compound_place(10**6)
  end

  def billions
    compound_place(10**9)
  end

  def tens_and_ones
    with_partial(n % 100) {|t| fetch(t){ compound_tens(t) }}
  end

  def compound_tens(x)
    [fetch(x / 10 * 10), fetch(x % 10)].compact.join("-")
  end

  def words
    [billions, millions, thousands, hundreds, tens_and_ones]
  end
end
