def evenly_divisible(dividend, divisor)
  (dividend % divisor).zero?
end

module Year
  def self.leap?(year)
    evenly_divisible(year, 4) and !evenly_divisible(year, 100) or evenly_divisible(year, 400)
  end
end
