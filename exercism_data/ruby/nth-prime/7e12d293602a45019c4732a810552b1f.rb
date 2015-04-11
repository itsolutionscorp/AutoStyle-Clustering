require 'prime'

class Prime
  def self.nth(number)
    raise ArgumentError if number == 0
    Prime.first(number)[-1]
  end
end
