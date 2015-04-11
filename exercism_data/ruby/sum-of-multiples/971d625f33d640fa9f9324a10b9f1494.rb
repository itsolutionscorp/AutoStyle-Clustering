class SumOfMultiples
  def initialize(*nums)
    @nums = nums
  end

  def to(num)
    multiples = []
    (0...num).each do |i|
      mult = false
      @nums.each do |j|
        mult = true if i % j == 0 
      end
      multiples << i if mult
    end
    multiples.reduce(0, :+)
  end

  def self.to(num)
    @nums = [3,5]
    multiples = []
    (0...num).each do |i|
      mult = false
      @nums.each do |j|
        mult = true if i % j == 0 
      end
      multiples << i if mult
    end
    multiples.reduce(0, :+)
  end
end
