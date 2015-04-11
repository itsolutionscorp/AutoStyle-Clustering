class Hamming
  
  def self.compute(a, b)
    Hamming.new(a,b).distance
  end

  def initialize(a,b)
    @a, @b = a, b 
  end

  def distance
    @distance ||= indexes.map { |idx| different_at?(idx) ? 1 : 0 }.reduce(0,:+)
  end

  private

  def indexes
    (0..@a.length-1)
  end
 
  def different_at?(idx)
    @b[idx] != @a[idx] && @b[idx].nil? == false 
  end  
end
