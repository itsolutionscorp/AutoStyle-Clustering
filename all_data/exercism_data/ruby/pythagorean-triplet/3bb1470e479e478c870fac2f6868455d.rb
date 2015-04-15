class Triplet

  def self.where(params={})
    min_factor = params.fetch(:min_factor, 1)
    max_factor = params.fetch(:max_factor)
    factor_sum = params.fetch(:sum, nil)
    
    triplets = (min_factor..max_factor).to_a.combination(3).map { |triplet| Triplet.new(*triplet) }

    triplets.select! { |triplet| triplet.sum == factor_sum } if factor_sum
    triplets.select! { |triplet| triplet.pythagorean? }
  end

  def initialize(a, b, c)
    @a, @b, @c = a, b, c
  end

  def product
    @a * @b * @c
  end

  def sum
    @a + @b + @c
  end

  def pythagorean?
    @a**2 + @b**2 == @c**2
  end

end
