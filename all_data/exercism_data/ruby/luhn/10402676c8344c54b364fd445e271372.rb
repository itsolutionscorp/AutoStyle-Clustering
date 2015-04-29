class Luhn
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def valid?
    checksum % 10 == 0
  end

  def addends
    self.class.make_addends(number)
  end

  def checksum
    self.class.make_checksum(addends)
  end

  def self.create(number)
    number*10 + last_digit(number)
  end

  private

  def self.last_digit(number)
    remainder = last_digit_of_checksum number
    remainder > 0 ? 10 - remainder : 0
  end

  def self.last_digit_of_checksum(number)
    make_checksum(make_addends number*10) % 10
  end

  def self.make_checksum(addends)
    addends.reduce(&:+)
  end

  def self.make_addends(number)
    digit = -1
    digits(number).reverse.map{ |num|
      addend_for_digit(num, digit += 1)
    }.reverse
  end

  def self.digits(number)
    number.to_s.chars.map(&:to_i)
  end

  def self.addend_for_digit(num, digit)
    (num if digit % 2 == 0) ||
    (num * 2 if num < 5) ||
    (num * 2 - 9)
  end
end
