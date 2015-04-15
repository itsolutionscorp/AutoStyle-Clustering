class Triplet

  def self.where(min_factor: 1, max_factor: 1, sum: nil)
    [*min_factor..max_factor].combination(2).map do |i, j|
      k = Math.sqrt(i**2 + j**2)
      if k == k.to_i && k <= max_factor
        candidate = Triplet.new(i, j, k.to_i)
        candidate if !sum || candidate.sum == sum
      end
    end.compact
  end

  def initialize a, b, c
    @triplet = [a, b, c].sort
  end

  def sum
    @triplet.reduce(:+)
  end

  def product
    @triplet.inject(:*)
  end

  def pythagorean?
    @triplet[0] ** 2 + @triplet[1]**2 == @triplet[2]**2
  end

end
