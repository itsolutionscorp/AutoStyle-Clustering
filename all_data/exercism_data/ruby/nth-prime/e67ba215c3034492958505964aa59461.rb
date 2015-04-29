require 'prime'
class Prime
  def self.nth(number)
    raise ArgumentError if number < 1
    counter = 1
    each { |prime| return prime if counter == number; counter += 1 }
  end
end
