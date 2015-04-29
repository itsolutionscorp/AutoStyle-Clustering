class Say
  MIN = 0
  MAX = 999_999_999_999

  def initialize(number)
    raise ArgumentError unless number.between?(MIN, MAX)

    @digits = number_to_digits(number)
  end

  def in_english
    return "zero" if @digits.join.to_i.zero?

    units = {
      0 => "billion", 1 => "million", 2 => "thousand"
    }
    chunks = break_into_chunks

    chunks.map.with_index do |chunk, idx|
      w = chunk_in_english(chunk)
      if units.key?(idx) and (not w.nil?)
        w << " #{units[idx]}"
      end

      w
    end.compact.join(" ")
  end

  private
  def number_to_digits(number)
    diff_len = MAX.to_s.length - number.to_s.length

    ("0" * diff_len + number.to_s).chars
  end

  def break_into_chunks
    @digits.reverse.each_slice(3).map do |chars|
      chars.reverse.join
    end.reverse
  end

  def chunk_in_english(chunk)
    case chunk.to_i
    when 0 .. 99
      number_in_english chunk.to_i
    when 100 .. 999
      if (chunk.to_i % 100).zero?
        "#{number_in_english(chunk[0].to_i)} hundred"
      else
        "#{number_in_english(chunk[0].to_i)} hundred #{number_in_english(chunk[1 .. -1].to_i)}"
      end
    else
      raise ArgumentError
    end
  end

  def number_in_english(number)
    return nil if number.zero?

    numbers_in_english = {
      90 => "ninety", 80 => "eighty", 70 => "seventy", 60 => "sixty",
      50 => "fifty", 40 => "forty", 30 => "thirty", 20 => "twenty",
      19 => "nineteen", 18 => "eighteen", 17 => "seventeen", 16 => "sixteen",
      15 => "fifteen", 14 => "fourteen", 13 => "thirteen", 12 => "twelve",
      11 => "eleven", 10 => "ten", 9 => "nine", 8 => "eight",
      7 => "seven", 6 => "six", 5 => "five", 4 => "four",
      3 => "three", 2 => "two", 1 => "one"
    }
    numbers_in_english.map do |k, v|
      if number >= k
        number -= k

        v
      end
    end.compact.join("-")
  end
end
