require 'prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n == 0
    numbers = 2..Float::INFINITY
    numbers.lazy.select do |x|
      prime?(x)
    end.first(n).last
  end
end
