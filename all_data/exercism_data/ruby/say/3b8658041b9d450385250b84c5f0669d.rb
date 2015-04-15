class Say
  ENGLISH_CONVERSIONS = {
    0 => 'zero',
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
    16 => 'sixteen',
    17 => 'seventeen',
    18 => 'eighteen',
    19 => 'nineteen',
    20 => 'twenty',
    30 => 'thirty',
    40 => 'forty',
    50 => 'fifty',
    60 => 'sixty',
    70 => 'seventy',
    80 => 'eighty',
    90 => 'ninety'
  }

  def initialize(num)
    raise ArgumentError unless (0..999_999_999_999).cover?(num)
    @num = num
  end

  def in_english
    result = ''

    if split_num[3].nonzero?
      result << english_conversion(split_num[3])
      result << ' billion '
    end
    if split_num[2].nonzero?
      result << english_conversion(split_num[2])
      result << ' million '
    end
    if split_num[1].nonzero?
      result << english_conversion(split_num[1])
      result << ' thousand '
    end
    if split_num[0].nonzero? || result.empty?
      result << english_conversion(split_num[0])
    end

    result.squeeze(' ').strip
  end

  private

  attr_reader :num

  def english_conversion(number)
    ENGLISH_CONVERSIONS.fetch(number) do
      num_chars = number.to_s.chars.reverse
      result = ''
      if num_chars[2].to_i.nonzero?
        result << " #{english_conversion("#{num_chars[2]}".to_i)} hundred "
      end
      if num_chars[1].to_i.nonzero?
        result << " #{english_conversion("#{num_chars[1]}0".to_i)}"
        if num_chars[0].to_i.zero?
          result << ' '
        else
          result << '-'
        end
      end
      if num_chars[0].to_i.nonzero?
        result << "#{english_conversion(num_chars[0].to_i)} "
      end
      result
    end
  end

  def split_num
    array = []
    num_chars = num.to_s.chars.reverse
    4.times do
      array << num_chars.shift(3).reverse.join.to_i
    end
    array
  end
end
