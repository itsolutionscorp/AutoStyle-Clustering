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
    theorem? && no_two_equal? 
  end

  def self.where(min_factor: 1, max_factor: 600, sum: nil)
    (min_factor..max_factor - 2).each_with_object([]) do |a, trips|
      (a + 1..max_factor - 1).each_with_object(trips) do |b, trips|
        (b + 1..max_factor).each_with_object(trips) do |c, trips|
          t = Triplet.new(a, b, c)
          trips.push t if t.pythagorean_with_sum? sum
        end
      end
    end
  end

  def pythagorean_with_sum?(sum)
    pythagorean? && (sum.nil? || self.sum == sum)
  end

  private
  def theorem?
    @a**2 + @b**2 == @c**2
  end

  def no_two_equal?
    @a != @b && @a != @c && @b != @c
  end

end
