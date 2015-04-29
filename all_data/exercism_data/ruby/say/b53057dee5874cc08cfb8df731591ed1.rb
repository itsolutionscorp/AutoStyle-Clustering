class Say
  def initialize(number)
    @number = number
  end

  def in_english
    raise ArgumentError if @number < 0
    say_zero || join_words(number_in_words)
  end

private

  ONES = { 1 => "one", 2 => "two", 3 => "three", 4 => "four",
           5 => "five", 6 => "six", 7 => "seven", 8 => "eight", 9 => "nine",
           10 => "ten", 11 => "eleven", 12 => "twelve", 13 => "thirteen",
           14 => "fourteen", 15 => "fifteen", 16 => "sixteen", 17 => "seventeen",
           18 => "eighteen", 19 => "nineteen" }

  TENS = { 2 => "twenty", 3 => "thirty", 4 => "forty", 5 => "fifty", 
           6 => "sixty", 7 => "seventy", 8 => "eighty", 9 => "ninety" }

  MAGNITUDE_WORDS = ["billion", "million", "thousand"]

  def say_zero
    "zero" if @number == 0
  end

  def join_words(words, delimiter: " ")
    words.flatten.compact.join delimiter
  end

  def number_in_words
    number_groups.zip(MAGNITUDE_WORDS).map do |group, magnitude_word|
      qualify words_of(group), magnitude_word      
    end
  end

  def number_groups
    [9, 6, 3].each_with_object([@number]) do |magnitude, parts|
      parts.push *(parts.pop.divmod(10 ** magnitude))
    end
  end

  def qualify(text, qualifier)
    [text, qualifier] unless [text].flatten.compact.empty?
  end

  def words_of(number)
    raise ArgumentError if number > 999
    hundreds, remainder = number.divmod(100)
    [say_hundreds(hundreds), say_small_number(remainder)]
  end

  def say_hundreds(number)
    qualify under_twenty(number), "hundred"
  end

  def say_small_number(number)
    under_twenty(number) || twenty_and_over(number)
  end

  def under_twenty(number)
    [ONES[number]] if number < 20
  end

  def twenty_and_over(number)
    tens, ones = number.divmod(10)
    join_words [tens_word(tens), under_twenty(ones)], delimiter: "-"
  end

  def tens_word(number)
    TENS[number]
  end
end
