require 'prime'

class Prime
  def self.nth(num)
    fail ArgumentError, 'The number must be positive' if num < 1
    take(num).last
  end
end
