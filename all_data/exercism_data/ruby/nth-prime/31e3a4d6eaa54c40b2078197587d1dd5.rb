require 'prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    Prime.take(n).last
  end
end
