class Say
  attr_reader :in_english
  SAY_UNDER_20 = {
    19 => 'ninteen',
    18 => 'eighteen', 
    17 => 'seventeen',
    16 => 'sixteen',
    15 => 'fifteen',
    14 => 'fourteen',
    13 => 'thirteen',
    12 => 'twelve',
    11 => 'eleven',
    10 => 'ten',
    9 => 'nine',
    8 => 'eight',
    7 => 'seven',
    6 => 'six',
    5 => 'five',
    4 => 'four',
    3 => 'three',
    2 => 'two',
    1 => 'one',
    0 => 'zero'
  }

  SAY_TENS = {
    9 => 'ninty',
    8 => 'eighty',
    7 => 'seventy',
    6 => 'sixty',
    5 => 'fifty',
    4 => 'forty',
    3 => 'thirty',
    2 => 'twenty',
    1 => 'ten',
    0 => 'zero'
  }

  SAY_VERY_LARGE = {
    1000000000 => 'billion',
    1000000 => 'million',
    1000 => 'thousand',
    100 => 'hundred'
  }
  
  ACCEPTED_RANGE = 0...10**12

  def initialize number
    raise ArgumentError unless ACCEPTED_RANGE.include?(number)
    @number = number
  end

  def in_english
    case @number
    when 0...20
      SAY_UNDER_20[@number]
    when 20...100
      tens, rem = @number.divmod 10
      return SAY_TENS[tens] if rem == 0
      return "#{SAY_TENS[tens]}-#{self.class.new(rem).in_english}"

    when 100...1000
      hundreds, rem = @number.divmod 100
      return "#{self.class.new(hundreds).in_english} hundred" if rem == 0
      return "#{self.class.new(hundreds).in_english} hundred #{self.class.new(rem).in_english}"

    when 1000...10**6
      thousands, rem = @number.divmod 1000
      return "#{self.class.new(thousands).in_english} thousand" if rem == 0
      return "#{self.class.new(thousands).in_english} thousand #{self.class.new(rem).in_english}"

    when 10**6...10**9
      millions, rem = @number.divmod 10**6
      return "#{self.class.new(millions).in_english} million" if rem == 0
      return "#{self.class.new(millions).in_english} million #{self.class.new(rem).in_english}"

    when 10**9...10**12
      billions, rem = @number.divmod 10**9
      return "#{self.class.new(billions).in_english} billion" if rem == 0
      return "#{self.class.new(billions).in_english} billion #{self.class.new(rem).in_english}"
    else
      raise ArgumentError
    end
  end

  def number_to_words number
    
  end
end
