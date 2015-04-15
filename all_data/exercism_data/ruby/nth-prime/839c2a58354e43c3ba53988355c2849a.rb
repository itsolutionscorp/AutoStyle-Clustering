require "prime"

class Prime
  def self.nth(n)
    fail ArgumentError if n <= 0
    take(n).last
  end
end
