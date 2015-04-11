class Say

  ONES = %w(zero one two three four five six seven eight nine)
  TEENS= %w(ten eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen)
  TENS = %w(zero ten twenty thirty forty fifty sixty seventy eighty ninety)

  scale_word = ['billion', 'million', 'thousand', 'hundred', '', '' ]
  scale_num  = [10**9, 10**6, 1000, 100, 10, 1]
  UNITS = scale_word.zip scale_num

  def initialize(num)
    @num = num
  end

  def in_english
    raise ArgumentError if @num < 0 || @num >= 10**12
    return 'zero' if @num.zero?
    output = []

    UNITS.each do |scale_word, scale_num|
      amt, @num = @num.divmod(scale_num)
      if amt > 0

        if scale_num == 10
          if amt == 1
            output << "#{TEENS[@num]}"
            @num = 0
          else
            output << "#{TENS[amt]}"
          end
        elsif scale_num == 1
          output << "#{ONES[amt]}"
        else
          output << "#{self.class.new(amt).in_english} #{scale_word}"
        end
        
      end
    end
    add_hyphen(output.join(' '))
  end

  private

  def add_hyphen(phrase)
    # only add hypen for > twenty
    TENS[2..-1].each do |word|  
      phrase.gsub!("#{word} ","#{word}-") if phrase.include?(word)
    end
    phrase
  end
end
