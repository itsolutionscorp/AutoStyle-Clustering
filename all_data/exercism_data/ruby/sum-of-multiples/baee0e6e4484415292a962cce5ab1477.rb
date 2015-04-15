class SumOfMultiples
  def initialize(*bases)
    @bases = bases
  end

  def to(limit)
    return 0 if limit == 1

    multiples = []
    @bases.each do |bm|
      (1...limit).each { |n| multiples << n if n % bm == 0 }
    end

    multiples.uniq.inject(:+)
  end

  def self.to(limit)
    SumOfMultiples.new(3, 5).to(limit)
  end
end
