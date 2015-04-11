class Luhn
  def self.create(initial)
    (0..9).each do |n|
      number = "#{initial}#{n}".to_i
      return number if new(number).valid?
    end
  end

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def digits
    number.to_s.chars.map(&:to_i)
  end

  def addends
    # select every second digit starting rightmost
    selected = digits.length % 2

    digits.each_with_index.map do |digit, index|
      if index % 2 == selected
        digit *= 2
        digit -= 9 if digit > 9
      end

      digit
    end
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end
end
