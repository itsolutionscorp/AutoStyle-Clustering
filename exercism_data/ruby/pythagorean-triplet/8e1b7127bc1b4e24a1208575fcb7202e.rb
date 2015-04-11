class Triplet
  def initialize(*args)
    @a,@b,@c = args.sort
  end

  def self.where(min_factor: 1, max_factor: 100, sum: nil)
    test_triplets(factors(min_factor,max_factor)).each_with_object([]) do |factors,triplets|
      test_triplet = self.new(*factors)
      next if sum != test_triplet.sum if sum
      triplets << test_triplet if test_triplet.pythagorean?
    end
  end

  def sum
    @a + @b + @c
  end

  def product
    @a * @b * @c
  end

  def pythagorean?
    @a ** 2 + @b ** 2 == @c ** 2
  end

  private

  def self.factors(min_factor,max_factor)
    (min_factor..max_factor).to_a
  end

  def self.test_triplets(factors)
    factors.product(factors, factors).map(&:sort).uniq
  end

end
