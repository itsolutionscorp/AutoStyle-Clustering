class Hamming
  def self.compute(string1, string2)
    pair = new(string1, string2)
    pair.distance
  end

  def initialize(*strands)
    @strands = strands
  end

  def distance
    return 0 if @strands.include? ""
    difference = equal_at?(0) ? 0 : 1
    difference + self.class.compute(*chopped_strands)
  end

  private

  def chopped_strands
    @strands.map {|strand| strand[1..-1]}
  end

  def equal_at?(position)
    @strands.map {|strand| strand[position]}.uniq.count == 1
  end
end
