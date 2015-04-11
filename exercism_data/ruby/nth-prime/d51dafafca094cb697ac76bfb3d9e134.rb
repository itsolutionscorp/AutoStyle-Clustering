require 'Prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    take(n).last
  end
end
