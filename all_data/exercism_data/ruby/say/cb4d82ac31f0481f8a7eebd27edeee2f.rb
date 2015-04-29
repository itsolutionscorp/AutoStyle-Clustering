class Say
  def initialize(n)
    raise ArgumentError if n < 0 || n >= 1_000_000_000_000
    @n = n.to_s.reverse.scan(/.{1,3}/).map(&:reverse)
    @english = {
      90 => "ninety", 80 => "eighty", 70 => "seventy", 60 => "sixty",
      50 => "fifty", 40 => "forty", 30 => "thirty", 20 => "twenty",
      19 => "nineteen", 18 => "eighteen", 17 => "seventeen", 16 => "sixteen",
      15 => "fifteen", 14 => "fourteen", 13 => "thirteen", 12 => "twelve",
      11 => "eleven", 10 => "ten", 9 => "nine", 8 => "eight", 7 => "seven",
      6 => "six", 5 => "five", 4 => "four", 3 => "three", 2 => "two", 1 => "one"
    }
    @places = ["", " thousand", " million", " billion"]
  end

  def in_english(n = @n)
    return "zero" if n == ["0"]
    n.each_with_index.map do |chunk, i|
      to_english(chunk.to_i) + (chunk == "000" ? "" : @places[i])
    end.reverse.select { |chunk| chunk.length > 0 }.join(" ").chomp(" ")
  end

  def to_english(n)
    english = ""
    hundreds = n / 100
    tens = n % 100 / 10 * 10
    ones = tens > 10 ? n % 10 : n % 100

    english += "#{@english[hundreds]} hundred#{' ' unless tens == 0}" if hundreds > 0
    english += "#{@english[tens]}#{'-' unless ones == 0}" if tens > 10
    english += "#{@english[ones]}" unless ones == 0
    english
  end
end
