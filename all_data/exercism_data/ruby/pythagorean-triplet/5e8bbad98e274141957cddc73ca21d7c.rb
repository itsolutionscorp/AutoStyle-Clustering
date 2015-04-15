class Triplet
  
  def self.where(options={})
    min_factor = options[:min_factor] || 0
    max_factor = options[:max_factor] || 1000
    sum = options[:sum]
    range = min_factor..max_factor

    search_triplets(range, sum)
  end

  attr_reader :a, :b, :c
  
  def initialize(a,b,c)
    @a,@b,@c = a, b, c
  end

  def sum
    @a + @b + @c
  end

  def product
    @a * @b * @c
  end

  def pythagorean?
    return false if [@a,@b,@c].any? {|x| x == 0}

    @a**2 + @b**2  == @c**2
  end

  def == (other_triplet)
    self.a == other_triplet.b && self.b == other_triplet.a
  end

  private

  def self.search_triplets(range, sum)
    range.to_a.repeated_permutation(3).each_with_object([]) do |(a,b,c), result|
      temp = Triplet.new(a, b, c)
      result << temp if fits_requirements?(temp, result, sum)
    end
  end

  def self.fits_requirements?(triplet, results, sum)
    unique_pythagorean = triplet.pythagorean? && !results.include?(triplet)

    sum.nil? ? unique_pythagorean : unique_pythagorean && triplet.sum == sum
  end

end
