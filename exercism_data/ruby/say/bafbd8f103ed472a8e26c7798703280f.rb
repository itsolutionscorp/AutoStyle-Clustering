class Say
  attr_reader :in_english
  ENGLISH_NUMBERS_TO_20 = {
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

  ENGLISH_NUMBERS_TO_100 = {
    90 => 'ninty',
    80 => 'eighty',
    70 => 'seventy',
    60 => 'sixty',
    50 => 'fifty',
    40 => 'fourty',
    30 => 'thirty',
    20 => 'twenty'
  }

  LARGE_ENGLISH_NUMBERS = {
    1000000000 => 'billion',
    1000000 => 'million',
    1000 => 'thousand',
    100 => 'hundred'
  }
  
  ACCEPTED_RANGE = 0...1000000000000

  def initialize number
    raise ArgumentError unless ACCEPTED_RANGE.include?(number)
    @number = number
  end

  def in_english
    return ENGLISH_NUMBERS_TO_20[@number] if @number < 20
    return number_to_words(number, "-") { |occurences, word| word } if number < 100
    return number_to_words(number, " ") { |occurences, word| number_to_words(occurences) + " " + word }
  end

  def number_to_words number
    
  end
end
