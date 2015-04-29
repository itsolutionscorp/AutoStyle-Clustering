require 'Prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n == 0
    self.first(n).last
  end
end
