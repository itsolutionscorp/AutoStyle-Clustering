class Say

  TENS = %W[
      zero
      ten
      twenty
      thirty
      forty
      fifty
      sixty
      seventy
      eighty
      ninety
    ]

  TEENS = %w[
      zero
      one
      two
      three
      four
      five
      six
      seven
      eight
      nine
      ten
      eleven
      twelve
      thirteen
      fourteen
      fifteen
      sixteen
      seventeen
      eighteen
      nineteen
    ]


  def initialize(number)
    @number = number
  end

  def in_english
    @english ||= to_english @number
  end

  private
  def to_english(number)
    raise ArgumentError unless number.between?(0,10**12)

    case number
    when (0...20)
      TEENS[number]
    when (20...100)
      tens, rest = number.divmod 10
      if rest.zero?
        TENS[tens]
      else
        "#{TENS[tens]}-#{to_english rest}"
      end
    when (100...1000)
      hundreds, rest = number.divmod 100
      if rest.zero?
        "#{to_english hundreds} hundred"
      else
        "#{to_english hundreds} hundred #{to_english rest}"
      end
    when (1000...10**6)
      thousands, rest = number.divmod 1000
      if rest.zero?
        "#{to_english thousands} thousand"
      else
        "#{to_english thousands} thousand #{to_english rest}"
      end
    when (10**6...10**9)
      millions, rest = number.divmod 10**6
      if rest.zero?
        "#{to_english millions} million"
      else
        "#{to_english millions} million #{to_english rest}"
      end
    when (10**9...10**12)
      billions, rest = number.divmod 10**9
      if rest.zero?
        "#{to_english billions} billion"
      else
        "#{to_english billions} billion #{to_english rest}"
      end
    else
      raise ArgumentError
    end
  end

end
