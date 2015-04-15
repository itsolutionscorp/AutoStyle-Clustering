class Hamming
  
  def self.compute(strand_a, strand_b)
    Hamming.new(strand_a,strand_b).distance
  end

  def initialize(a,b)
    @a, @b = a, b 
  end

  def distance
    @distance ||= indexes.count { |idx| different_at?(idx) } 
  end

  private

  def indexes
    (0...[@a.length, @b.length].min)
  end
 
  def different_at?(idx)
    @b[idx] != @a[idx]
  end  
end
