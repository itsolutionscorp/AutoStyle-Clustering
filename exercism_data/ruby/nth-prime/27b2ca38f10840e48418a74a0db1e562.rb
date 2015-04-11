require 'prime'

class Prime

  def self.nth(number)
    throw ArgumentError if number.zero?
    i = 0
    count = 0
    loop do
      return i if count == number
      i += 1
      count += 1 if Prime.prime?(i)
    end
  end



  def self.prime?(number)
    return false if number <= 1
    (2..number).each do |n|
      return false if (number % n == 0) && (n != number)
    end
    true
  end

end
