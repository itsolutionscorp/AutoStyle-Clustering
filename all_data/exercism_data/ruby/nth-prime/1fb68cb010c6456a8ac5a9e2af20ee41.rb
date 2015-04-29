require 'prime'

class Prime
  def self.nth(n)
    raise ArgumentError unless (n.is_a? Integer) && n > 0
    first(n).last
  end
end
