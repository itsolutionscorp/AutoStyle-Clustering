module InEnglish
  SMALL_NUMBERS =
    %w( zero one two three four five six seven eight nine ten eleven twelve
        thirteen fourteen fifteen sixteen seventeen eighteen nineteen )
  TENS =
    %w( zero ten twenty thirty forty fifty sixty seventy eighty ninety )

  refine NilClass do
    def say; end
  end

  refine Integer do
    def say
      case self
      when (0...20)
        SMALL_NUMBERS.fetch(self)
      when (20...100)
        [TENS.fetch(div 10), modulo(10).nonzero?.say].compact.join('-')
      when (100...1000)
        say_divmod(100, 'hundred')
      when (1000...1_000_000)
        say_divmod(1000, 'thousand')
      when (1_000_000...1_000_000_000)
        say_divmod(1_000_000, 'million')
      when (1_000_000_000...1_000_000_000_000)
        say_divmod(1_000_000_000, 'billion')
      else
        fail ArgumentError
      end
    end

    private

    def say_divmod(n, word)
      [div(n).say, word, modulo(n).nonzero?.say].compact.join(' ')
    end
  end
end

class Say
  using InEnglish

  attr_reader :in_english

  def initialize(number)
    @in_english = number.say
  end
end
