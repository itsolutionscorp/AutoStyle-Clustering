require 'prime'
class Prime
  def self.nth(n)
    raise ArgumentError, "should be positive integer" unless n > 0 && n.integer?
    list = Prime.first n
    list[-1]
  end
end
