class Triplet
  def initialize(a, b, c)
    @a = a
    @b = b
    @c = c
  end

  def sum(a = @a, b = @b, c = @c)
    a + b + c
  end

  def product(a = @a, b = @b, c = @c)
    a * b * c
  end

  def pythagorean?(a = @a, b = @b, c = @c)
    a**2 + b**2 == c**2
  end

  def self.where(options = {})
    min = options[:min_factor] || 1
    max = options[:max_factor]
    matrix = []
    result = []
    (min..max).each do |c|
      (min..c-1).each do |b|
        (b+1..c-1).each do |a|
          matrix << [a, b, c]
        end
      end
    end
    if options[:sum]
      matrix.select! do |trip|
        test_triplet = Triplet.new(trip[0], trip[1], trip[2])
        test_triplet.sum == options[:sum]
      end
    end
    matrix.select! do |trip|
      triplet = Triplet.new(trip[0], trip[1], trip[2])
      triplet.pythagorean?
    end
    matrix.each do |trip|
      triplet = Triplet.new(trip[0], trip[1], trip[2])
      result << triplet
    end
    return result
  end
end
