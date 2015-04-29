class Say
  def initialize(number)
    @number = number
  end

  def in_english
    validate_positive
    if @number < 20
      ones_word @number
    elsif @number < 100
      tens, ones = @number.divmod(10)
      [tens_word(tens), say_unless_zero(ones)].compact.join "-"
    else
      factors
    end
  end

private

  ONES = { 0 => "zero", 1 => "one", 2 => "two", 3 => "three", 4 => "four",
           5 => "five", 6 => "six", 7 => "seven", 8 => "eight", 9 => "nine",
           10 => "ten", 11 => "eleven", 12 => "twelve", 13 => "thirteen",
           14 => "fourteen", 15 => "fifteen", 16 => "sixteen", 17 => "seventeen",
           18 => "eighteen", 19 => "nineteen" }

  TENS = { 2 => "twenty", 3 => "thirty", 4 => "forty", 5 => "fifty", 
           6 => "sixty", 7 => "seventy", 8 => "eighty", 9 => "ninety" }

  def validate_positive
    raise ArgumentError if @number < 0
  end

  def ones_word(number)
    ONES[number]
  end

  def tens_word(number)
    TENS[number]
  end

  def say_unless_zero(number)
    self.class.new(number).in_english unless number == 0
  end

  MAGNITUDES = { 2 => "hundred", 3 => "thousand", 6 => "million", 9 => "billion" }

  def factors
    factor, word = MAGNITUDES.find { |magnitude,| @number < 10 ** magnitude } or raise ArgumentError
    units, remainder = @number.divmod(10 ** factor)
    [say_unless_zero(units), word, say_unless_zero(remainder)].compact.join " "
  end


end
