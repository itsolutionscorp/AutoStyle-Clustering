class Say
  def initialize(number)
    @number = number
  end

  def in_english
    if @number < 0 || @number >= 10 ** 12
      raise ArgumentError
    elsif @number < 20
      ones_word @number
    elsif @number < 100
      tens, ones = @number.divmod(10)
      [tens_word(tens), say_unless_zero(ones)].compact.join "-"
    elsif @number < 1000
      hundreds, remaining = @number.divmod(100)
      [say_unless_zero(hundreds), "hundred", say_unless_zero(remaining)].compact.join " "
    elsif @number < 1000000
      thousands, remaining = @number.divmod(1000)
      [say_unless_zero(thousands), "thousand", say_unless_zero(remaining)].compact.join " " 
    elsif @number < 10 ** 9 
      millions, remaining = @number.divmod(1000000)
      [say_unless_zero(millions), "million", say_unless_zero(remaining)].compact.join " " 
    elsif @number < 10 ** 12
      billions, remaining = @number.divmod(10 ** 9)
      [say_unless_zero(billions), "billion", say_unless_zero(remaining)].compact.join " " 
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


  def ones_word(number)
    ONES[number]
  end

  def tens_word(number)
    TENS[number]
  end

  def say_unless_zero(number)
    self.class.new(number).in_english unless number == 0
  end


end
