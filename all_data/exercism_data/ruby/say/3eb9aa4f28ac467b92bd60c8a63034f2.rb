class Say

  N_TO_WORDS = {
    0 => { teen: "ten" },
    1 => { normal: "one", teen: "ten", ty: "eleven" },
    2 => { normal: "two", teen: "twleve", ty: "twenty" },
    3 => { normal: "three", teen: "thirteen", ty: "thirty" },
    4 => { normal: "four", teen: "fourteen", ty: "forty" },
    5 => { normal: "five", teen: "fifteen", ty: "fifty" },
    6 => { normal: "six", teen: "sixteen", ty: "sixty" },
    7 => { normal: "seven", teen: "seventeen", ty: "seventy" },
    8 => { normal: "eight", teen: "eighteen", ty: "eighty" },
    9 => { normal: "nine", teen: "nineteen", ty: "ninety" }
  }

  MAGNITUDE_WORD = {
    1_000_000_000_000 => :trillion,
    1_000_000_000     => :billion,
    1_000_000         => :million,
    1_000             => :thousand
  }

  def initialize(n)
    @n = n
  end

  def in_english
    raise ArgumentError unless @n.between?(0, 999_999_999_999)
    return "zero" if @n.zero?
    say = MAGNITUDE_WORD.reduce(words: [], value: @n) do |result, (magnitude, name)|
      q, r = result[:value].divmod(magnitude)
      result[:words] << "#{meta_words(q)} #{name}" if q > 0
      result.merge(value: r)
    end
    say[:words] << meta_words(say[:value]) unless say[:value].zero?
    say[:words].join(" ")
  end

  private

  def meta_words(n)
    "#{hundred(n)} #{below_hundred(n.modulo(100))}".strip
  end

  def hundred(n)
    q, r = n.divmod(100)
    q.zero? ? nil : "#{N_TO_WORDS[q][:normal]} hundred"
  end

  def below_hundred(n)
    q, r = n.divmod(10)
    ty_word = N_TO_WORDS[q][:ty] if q > 1
    teen_word = q == 1 ? N_TO_WORDS[r][:teen] : N_TO_WORDS[r][:normal]
    [ty_word, teen_word].compact.join("-")
  end
end
