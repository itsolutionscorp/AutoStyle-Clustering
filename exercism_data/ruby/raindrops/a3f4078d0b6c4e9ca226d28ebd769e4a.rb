module Raindrops
  def self.convert(nr)
    "".tap do |result|
      result << "Pling" if divisable_without_remainder?(nr, 3)
      result << "Plang" if divisable_without_remainder?(nr, 5)
      result << "Plong" if divisable_without_remainder?(nr, 7)
      result << nr.to_s if result.empty?
    end
  end

  def self.divisable_without_remainder?(nr, divisor)
    (nr % divisor) == 0
  end
end
