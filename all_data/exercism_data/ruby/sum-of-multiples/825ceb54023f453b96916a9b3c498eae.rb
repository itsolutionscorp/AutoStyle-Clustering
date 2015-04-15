class SumOfMultiples
  def initialize(*divs)
    @divs = divs
  end

  def self.to(max, divs: [3, 5])
    (1 .. (max - 1)).select do |n|
      divs.select { |d| (n % d).zero? }.length >= 1
    end.inject(0, :+)
  end

  def to(max)
    self.class.to(max, divs: @divs)
  end
end
