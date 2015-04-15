class SumOfMultiples
  def initialize *args
    @args = args
  end

  def self.to n
    new(3, 5).to(n)
  end

  def to num
    multiples = [0]
    (1...num).to_a.each do |n|
      @args.each do |i|
        multiples << n if n % i == 0
      end
    end
    multiples.uniq.inject(:+)
  end
end
