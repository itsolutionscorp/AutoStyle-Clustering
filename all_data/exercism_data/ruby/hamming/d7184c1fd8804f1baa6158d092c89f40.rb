class Hamming

  def self.compute(a, b)
    new(a, b).compute      
  end
    
  def initialize(a, b)
    @a = a
    @b = b
  end

  def compute
    (0...minimum_length).inject(0) do |sum, index|
      sum + difference_at(index)
    end
  end

private

  def difference_at(index)
    different_at?(index) ? 1 : 0 
  end

  def different_at?(index)
    @a[index] != @b[index]
  end

  def minimum_length
    [@a.length, @b.length].min
  end

end
