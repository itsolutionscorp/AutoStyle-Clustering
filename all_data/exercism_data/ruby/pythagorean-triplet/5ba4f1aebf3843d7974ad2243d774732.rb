class Triplet
  def initialize(a, b, c)
    @a, @b, @c = a, b, c
  end

  def sum
    @a + @b + @c
  end

  def product
    @a * @b * @c
  end

  def pythagorean?
    @a**2 + @b**2 == @c**2
  end

  def self.where(**args)
    res = []
    min = args[:min_factor] || 1
    max = args[:max_factor] || 100
    given_sum = args[:sum] || 0

    min.upto(max - 2) do |a|
      (a + 1).upto(max - 1) do |b|
        (b + 1).upto(max) do |c|
          t = Triplet.new(a, b, c)
          next unless t.pythagorean?
          res << t if given_sum == 0 or given_sum == t.sum
        end
      end
    end
    res
  end
end
