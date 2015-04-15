class Triplet
  def initialize(*args)
    @a,@b,@c = args.sort
  end

  def self.where(opts={})
    triplets = []
    max_factor = opts[:max_factor]
    min_factor = opts[:min_factor] || 1
    sum = opts[:sum]
    factors = (min_factor..max_factor).to_a
    test_triplets(factors).each do |a,b,c|
      test_triplet = self.new(a,b,c)
      if sum
        next if sum != test_triplet.sum
      end
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

  def test_triplets(factors)
    factors.product(factors, factors).map(&:sort).uniq
  end
end
