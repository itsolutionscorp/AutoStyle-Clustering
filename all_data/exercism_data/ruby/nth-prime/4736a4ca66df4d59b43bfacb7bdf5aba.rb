require 'prime'

class Prime
  RANGE = 1..Float::INFINITY

  def self.nth(n)
    raise ArgumentError, "n = #{n} is not a valid input" unless n > 0
    RANGE.lazy.select { |number| number.prime? }.take(n).max
  end

end
