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
    @a**2 + @b**2 == @c**2
  end

  def self.where(opts)
    throw ArgumentError if opts[:max_factor].nil?

    results = []
    min = opts[:min_factor].nil? ? 3 : opts[:min_factor]
    max = opts[:max_factor]
    sum = opts[:sum]

    min.upto(max) do |a|
      a.upto(max) do |b|
        b.upto(max) do |c|
          if sum.nil? || a + b + c == sum
            results << self.new(a,b,c) if a*a + b*b == c*c
          end
        end
      end
    end

    results
  end
end
