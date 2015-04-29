class Hamming
  
  def self.compute(ancestor, descendant)
    ancestor.size > descendant.size ?
    compare(descendant, ancestor) : compare(ancestor, descendant)
  end

  def self.compare(first, second)
    count = 0
    first.split(//).each_with_index { |c, i| count += diff_count(c , second[i]) }
    count
  end

  def self.diff_count(x, y)
    x == y ? 0 : 1
  end
end
