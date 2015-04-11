require 'prime'

class Prime
  def self.nth number
    raise ArgumentError.new if number < 1
    Prime.take(number).last
  end
end
