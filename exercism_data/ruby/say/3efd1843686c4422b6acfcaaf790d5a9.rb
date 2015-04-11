class Say

  LOWER_LIMIT = 0
  UPPER_LIMIT = 999_999_999_999
  NUMBERS_TO_WORDS = {
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
    10 => 'ten',
    9 =>  'nine',
    8 =>  'eight',
    7 =>  'seven',
    6 =>  'six',
    5 =>  'five',
    4 =>  'four',
    3 =>  'three',
    2 =>  'two',
    1 =>  'one'
  }


  def initialize(n)
    @n = n
  end

  def in_english
    raise ArgumentError unless @n.between?(LOWER_LIMIT, UPPER_LIMIT)
    return "zero" if @n.zero?

    format_triplets(get_triplets)
  end

  def get_triplets
    chunk(@n).map { |triplet| words_for_triplet(triplet) }
  end

  def format_triplets(triplets)
    interpolate(triplets).compact.join(" ")
  end

  def words_for_triplet(n)
    hundreds_place, remainder = n.divmod(100)
    return if hundreds_place.zero? && remainder.zero?

    first_bit = words_for_hundreds(hundreds_place)
    last_bit = words_for_under_one_hundred(remainder)
    [first_bit, last_bit].compact.join(" ")
  end

  def words_for_hundreds(hundreds_place)
    if hundreds_place.nonzero?
      hundreds_place_word = words_for_under_one_hundred(hundreds_place)
      "#{hundreds_place_word} hundred"
    end
  end

  def words_for_under_one_hundred(n)
    return if n.zero?
    first_value, first_word = find_largest_not_exceeding(n)
    remainder = n - first_value
    second_word = NUMBERS_TO_WORDS[remainder]
    if second_word
      "#{first_word}-#{second_word}"
    else
      first_word
    end
  end

  def find_largest_not_exceeding(n)
    NUMBERS_TO_WORDS.each do |value, word|
      return value, word if value <= n
    end
  end

  def interpolate(chunks)
    last = chunks.pop
    length = chunks.length
    orders = ['billion', 'million', 'thousand'].last(length)
    result = []
    while word = chunks.shift
      result << word
      result << orders.shift
    end
    result << last
    result
  end

  def chunk(n)
    n.to_s.chars.reverse.each_slice(3).map { |triplet| triplet.reverse.join.to_i }.reverse
  end

end
