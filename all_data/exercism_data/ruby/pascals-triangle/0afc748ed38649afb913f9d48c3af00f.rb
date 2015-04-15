class Triangle
  attr_reader :rows
  def initialize size
    @rows = size.times.with_object([]) do |i,list|
      list << generate_row(list[i-1].to_a)
    end
  end
  
  private
  def generate_row prior
    (prior.length+1).times.with_object([]) do |i, row|
      prev = i.zero? ? 0 : prior[i-1]
      row << pascal_value(prev, prior[i].to_i)
    end
  end
  
  def pascal_value left, right
    sum = left+right
    val = sum.zero? ? 1 : sum
  end
end
