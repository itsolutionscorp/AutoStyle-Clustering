class Luhn
  attr_reader :numbers

  def initialize(input)
    @numbers = input
  end

  def addends
    process_flipped.reverse
  end

  def checksum
    addends.inject(0) { |sum, n| sum += n }
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(number)
    if Luhn.new(number).valid?
      number
    else
      find_check(number)
    end
  end

  private

  def split_and_flip
    numbers.to_s.chars.reverse.map do |c|
      c.to_i
    end
  end

  def double_every_other
    arr = split_and_flip
    arr.each_with_index do |n, i|
      arr[i] = (n * 2) if i.odd?
    end
    arr
  end

  def process_flipped
    double_every_other.collect do |n|
      correct_if_over_ten(n)
    end
  end

  def correct_if_over_ten(n)
    n -= 9 if n >= 10
    n
  end

  def self.find_check(num)
    9.times do |i|
      test = Luhn.new(num * 10 + i)
      return test.numbers if test.valid?
    end
  end

end
