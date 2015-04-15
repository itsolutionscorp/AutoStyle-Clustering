# assert_equal 0, Hamming.compute('A', 'A')
class Hamming

  def self.compute(a, b)
    new(a, b).compute
  end

  attr_reader :a, :b

  def initialize(a, b)
    @a = a
    @b = b
  end

  def compute
    (0..length).reduce(0) do |count, i|
      @a[i] != @b[i] ? count+1 : count
    end
  end

  private 
  
  def length
    ([a.length, b.length].min) - 1
  end

end
