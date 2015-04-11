require 'prime'

class Prime
  def self.nth(n)
    raise ArgumentError unless n > 0

    each_with_index do |prime, i|
      return prime if i == n - 1
    end
  end
end
