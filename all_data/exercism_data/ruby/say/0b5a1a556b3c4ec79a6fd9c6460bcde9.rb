class Say
  def initialize(number)
    raise ArgumentError unless (0...10**12).include?(number)
    @number = number
  end

  DIMENSIONS = {
    10**9 => 'billion',
    10**6 => 'million',
    10**3 => 'thousand',
    10**2 => 'hundred'
  }

  TENS = {
    9 => 'ninety',  8 => 'eighty',
    7 => 'seventy', 6 => 'sixty',
    5 => 'fifty',   4 => 'forty',
    3 => 'thirty',  2 => 'twenty'
  }

  REMAINDER = {
    19 => 'nineteen',  18 => 'eighteen',
    17 => 'seventeen', 16 => 'sixteen',
    15 => 'fifteen',   14 => 'fourteen',
    13 => 'thirteen',  12 => 'twelve',
    11 => 'eleven',    10 => 'ten',
    9 => 'nine',  8 => 'eight', 7 => 'seven',
    6 => 'six',   5 => 'five',  4 => 'four',
    3 => 'three', 2 => 'two',   1 => 'one'
  }

  def in_english
    return "zero" if @number == 0

    english_words = DIMENSIONS.map do |scale, name|
      how_many, @number = @number.divmod(scale)
      if how_many > 0
        [Say.new(how_many).in_english, name]
      end
    end

    tens, modulus = @number.divmod(10)
    english_words << (tens < 2 ? REMAINDER[@number] :
      [TENS[tens], REMAINDER[modulus]].compact.join('-'))
    english_words.compact.join(' ')
  end

end
