class Triplet
  def initialize(a, b, c)
    @a = a
    @b = b
    @c = c
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

  def self.where(min_factor: 1, max_factor:, sum: nil)
    # a bit of mathematical background to explain
    # why no two numbers can be the same
    #
    # a and c, and b and c cannot be the same unless
    # b or a == 0 (which doesn't really make a tripel)
    #
    # a and b cannot be the same, because then:
    # a^2 and b^2 only contain each factor twice (since they are squares)
    # a == b, so a^2 + b^2 == 2*a^2. this cannot be a square, since it would
    # require an uneven number of factors of 2

    calculate_c =
      if sum
        ->(a,b) { sum - a - b }
      else
        ->(a,b) { Math.sqrt(a**2 + b**2).round }
      end

    as_and_bs = min_factor.upto(max_factor - 2).map do |a|
      (a + 1).upto(max_factor - 1).map do |b|
        [a, b]
      end
    end.inject(:+)

    as_and_bs
      .map do |a,b|
          c = calculate_c.(a,b)
          Triplet.new(a,b,c)
        end
      .select(&:pythagorean?)
  end

end
