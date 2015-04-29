class SumOfMultiples

  def initialize(*numbers)
    @numbers = numbers
  end

  def to(to_number)
    self.class.to(to_number, @numbers)
  end

  def self.to(to_number, numbers = [3,5])
    result = []

    numbers.each do |number|
      multiplier = 0
      while (current = number * multiplier) < to_number
        result << current
        multiplier += 1
      end

    end

    return 0 unless result.length > 0
    result.uniq.reduce(:+)
  end
end
