# Allows use of the extremely intuitive syntax:
# Say(1234).in_english
def Say(number)
  Say.new(number)
end

class Say
  attr_reader :number

  DIGIT = %w(
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
  )

  TENS = %w(
    twenty
    thirty
    forty
    fifty
    sixty
    seventy
    eighty
    ninety
  )

  def initialize(num)
    fail ArgumentError unless (0...1_000_000_000_000).include?(num)
    @number = num
  end

  def in_english
    case number
    when 0..19
      DIGIT[number]
    when 20..99
      tens, one = number.divmod(10)
      "#{TENS[tens - 2]}-#{DIGIT[one]}"
    else
      name, divisor = magnitude
      whole, remainder = number.divmod(divisor)
      self.class.new(whole).in_english + " #{name} " + self.class.new(remainder).in_english
    end.gsub(/[\s-]zero$/, '')
  end

  def magnitude
    case number
    when 100..999
      ['hundred', 100]
    when 1000..999_999
      ['thousand', 1000]
    when 1_000_000..999_999_999
      ['million', 1_000_000]
    else
      ['billion', 1_000_000_000]
    end
  end
end
