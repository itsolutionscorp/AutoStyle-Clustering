class SumOfMultiples
  attr_accessor :multi_1, :multi_2, :multi_3

  def initialize m = 3, m2 = 5, *m3
    self.multi_1 = m
    self.multi_2 = m2
    self.multi_3 = m3
  end

  def to num
    return 0 if num < 3
    multi_3.push(multi_1); multi_3.push(multi_2)
    sum = []
    pr = Proc.new { sum += (1...num).select{ |elem| elem % @var == 0} }

    multi_3.each do |var|
      @var = var
      pr.call(@var)
    end
    sum.uniq.inject(:+)
  end

  def self.to num
    return 0 if num < 3
    (1...num).select { |elem| elem % 3 == 0 || elem %  5 == 0}.map.inject(:+)
  end
end
