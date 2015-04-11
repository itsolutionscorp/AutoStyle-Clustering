class SumOfMultiples

  def initialize(*factors)
    @factors = factors
  end

  def to(limit)
    SumOfMultiples.to(limit, @factors)
  end

  def self.to(limit, factors = [3,5])
    sum = 0
    (1...limit).each do |number|
      factors.each do |factor|
        if number.modulo(factor).zero?
          sum += number
          break
        end
      end
    end
    sum
  end

end
