class SumOfMultiples

  attr_reader :numbers

  def initialize(*numbers)
    @numbers = numbers
  end

  def self.to(limit)
    new(3, 5).to(limit)
  end

  def to(limit)
    numbers.min > limit ? 0 :
      (1..(limit-1)).select {|n| multiple?(n)}.reduce(:+)
  end

  private

  def multiple?(num)
    numbers.any? {|n| num % n == 0}
  end

end
