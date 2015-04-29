class SumOfMultiples
  def initialize *multiples
    @multiples = multiples
  end

  def self.to ceiling, multiples = [3, 5]
    total = 0
    (1..multiples.length).each do |subset_length|
      sign = subset_length.odd? ? 1 : -1
      multiples.combination(subset_length) do |subset|
        lcm = subset.reduce(:lcm)
        total += sign * (sum_of_multiples_single lcm, ceiling)
      end
    end
    return total
  end

  def to ceiling
    return self.class.to ceiling, @multiples
  end
  
  private
    def self.sum_of_multiples_single multiple, ceiling
      count = (ceiling-1) / multiple
      multiple * count * (count + 1) / 2
    end
end
