class Say
  def initialize number
    raise ArgumentError unless number.between? 0, 999_999_999_999
    @number = number
  end

  def in_english
    return 'zero' if @number == 0
    chunks = [9, 6, 3, 0].map { |i| @number % 10**(i+3) / 10**i }
    words = chunks.zip(POWERS).flat_map do |n, p| 
      [self.class.up_to_999_in_english(n), p] unless n == 0
    end
    words.compact.join ' '
  end

  private
    def self.up_to_999_in_english number
      hundreds, remainder = number.divmod 100
      words = [SINGLES[hundreds], ('hundred' unless hundreds == 0)]
      if remainder < 20
        words << SINGLES[remainder]
      else
        tens, ones = remainder.divmod 10
        words << ([TENS[tens], SINGLES[ones]].compact.join '-')
      end
      words.compact.join ' '
    end

    SINGLES = [nil] + %w(one two three four five six seven eight nine ten 
                         eleven twelve thirteen fourteen fifteen sixteen 
                         seventeen eighteeen nineteen)
    TENS = [nil] + %w(ten twenty thirty forty fifty sixty seventy eighty ninety)
    POWERS = %w(billion million thousand) 
end
