class Triplet
  def initialize(*args)
    @a,@b,@c = args.sort
  end

  def self.where(min_factor: 1, max_factor: 100, sum: nil)
    factors = (min_factor..max_factor).to_a
    triplets = []
    test_triplets(factors).each do |a,b,c|
      test_triplet = self.new(a,b,c)
      next if sum != test_triplet.sum if sum
      triplets << test_triplet if test_triplet.pythagorean?
    end
    triplets
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

  def self.test_triplets(factors)
    factors.product(factors, factors).map(&:sort).uniq
  end
end
