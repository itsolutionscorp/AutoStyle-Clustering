require 'Prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    each.take(n).last
  end
end
