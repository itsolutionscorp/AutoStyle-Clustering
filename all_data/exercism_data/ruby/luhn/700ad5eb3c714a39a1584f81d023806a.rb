class Fixnum
  def digits
    to_s.chars.map(&:to_i)
  end
end

module Enumerable
  def reduce_with_index(memo, &block)
    acc = memo
    each_with_index do |item, i|
      acc = yield acc, item, i
    end
    acc
  end
end

class Luhn
  def initialize(number)
    @number = number
  end

  def valid?
    checksum % 10 == 0
  end

  def addends
    @addends ||= self.class.make_addends(@number)
  end

  def checksum
    @checksum ||= self.class.make_checksum(addends)
  end

  def self.create(number)
    number*10 + last_digit(number)
  end

  private

  def self.make_addends(number)
    number.digits.reverse.reduce_with_index([]) do |addends, num, digit|
      addends.unshift addend_for_digit(num, digit)
      addends
    end
  end

  def self.addend_for_digit(num, digit)
    (num if digit % 2 == 0) or
    (num * 2 if num < 5) or
    (num * 2 - 9)
  end


  def self.make_checksum(addends)
    addends.reduce(0, &:+)
  end


  def self.last_digit(number)
    remainder = last_digit_of_checksum number
    (10 - remainder if remainder > 0) or 0
  end

  def self.last_digit_of_checksum(number)
    make_checksum(make_addends number*10) % 10
  end

end
