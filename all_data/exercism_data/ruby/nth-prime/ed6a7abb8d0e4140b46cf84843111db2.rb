require 'prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n == 0
    prime, _ = Prime.each.with_index.detect { |_, i| i == (n - 1) }
    prime
  end
end
