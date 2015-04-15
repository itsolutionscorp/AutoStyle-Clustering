class SumOfMultiples
  def self.find_sum(n, multiples)
    (multiples[0]..n-1).reduce(0) do |sum, k|
      if multiples.any? { |m| k % m == 0 } 
        sum + k
      else 
        sum 
      end
    end
  end

  def self.to(n)
    find_sum(n, [3, 5])
  end

  def initialize(*args)
    @numbers = args
  end

  def to(n)
    SumOfMultiples.find_sum(n, @numbers)
  end
end
