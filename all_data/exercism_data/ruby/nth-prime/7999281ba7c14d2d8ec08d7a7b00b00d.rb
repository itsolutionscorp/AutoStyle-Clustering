require 'prime'

class Prime
  def self.nth(number)
    raise ArgumentError unless number.is_a?(Integer) && number > 0
    Prime.first(number).last
  end
end
