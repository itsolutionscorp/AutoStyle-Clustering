require 'prime'

class Prime
  def self.nth(n)
    fail ArgumentError, 'must be positive' if n < 1
    take(n).last
  end
end
