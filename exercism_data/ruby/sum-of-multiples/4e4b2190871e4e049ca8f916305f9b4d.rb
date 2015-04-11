class SumOfMultiples

  def self.to(n)
    new(3, 5).to(n)
  end

  def initialize(*multiples_of)
    @multiples_of = multiples_of
  end

  def to(n)
    @to = n
    sum
  end

  def sum
    multiples = []
    @to.times do |i|
      @multiples_of.each do |x|
        if i % x == 0
          multiples << i
        end
      end
    end
    multiples.uniq.inject(:+)
  end

end
