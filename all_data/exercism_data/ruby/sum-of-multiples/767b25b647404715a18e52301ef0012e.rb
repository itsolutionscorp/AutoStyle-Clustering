class SumOfMultiples
  def self.to(num)
    self.new.to(num)
  end

  def initialize(*factors)
    @factors = factors.any? ? factors : [3,5]
  end

  def to(num)
    1.upto(num-1).lazy.select do |num|
      multiple?(num)
    end.inject(0, :+)
  end

  private

  def multiple?(number)
    @factors.any? { |factor| number % factor == 0 }
  end

end
