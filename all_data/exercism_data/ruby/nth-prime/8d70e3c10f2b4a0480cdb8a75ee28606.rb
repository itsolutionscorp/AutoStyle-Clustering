require 'prime'

class Prime
  def self.nth(num)
    raise ArgumentError if num <= 0
    Prime.take(num).last
  end
end
