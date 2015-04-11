class Say

  VALUES = {
    1_000_000_000 => "billion",
    1000000 => "million",
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
    9 => "nine",
    8 => "eight",
    7 => "seven",
    6 => "six",
    5 => "five",
    4 => "four",
    3 => "three",
    2 => "two",
    1 => "one"
  }

  def initialize(num)
    @num = num
  end

  def in_english
    raise ArgumentError unless (0..999_999_999_999).include?(@num)
    return "zero" if @num == 0
    
    results = []
    chunks = chunk_to_thousands(@num)
    chunks.reverse.each_with_index do |part, index|
      unless part.to_i.zero?
        results << translate_part(part.to_i * 1000 ** index)
      end
    end
    results.reverse.join(" ")
  end

  private

  def position_value(index, array)
    1000 ** (array.length - (index + 1))
  end

  def chunk_to_thousands(num)
    num.to_s.chars.reverse.each_slice(3).map(&:reverse).map(&:join).reverse
  end

  def translate_part(num)
    remaining = num
    previous_element = 0
    VALUES.inject("") do |result, (num_i, num_s)|
      if remaining >= num_i
        counter = remaining / num_i
        
        case
        when num_i >= 100
          result << " " unless result.empty?
          result << "#{Say.new(counter).in_english} #{num_s}"
        when num_i.between?(10,99)
          result << " " unless result.empty?
          result << num_s
        when num_i < 10
          if previous_element >= 100
            result << " "
          else
            result << "-" unless result.empty?
          end
          result << num_s
        end
        remaining = remaining - counter * num_i
        previous_element = num_i
      end
      result
    end
  end
end
