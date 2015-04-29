class Triplet
  class << self
    def where(options = {})
      options.each_with_object(TripletGenerator.new) do |(key, value), gen|
        gen.send(key, value)
      end
    end
  end

  def initialize(*factors)
    @a, @b, @c = factors.sort
  end

  def sum
    factors.inject(:+)
  end

  def product
    factors.inject(:*)
  end

  def pythagorean?
    a ** 2 + b ** 2 == c ** 2
  end

  private

  attr_reader :a, :b, :c

  def factors
    [a, b, c]
  end

  class TripletGenerator
    include Enumerable

    def max_factor(f)
      @max = f
    end

    def min_factor(f)
      @min = f
    end

    def sum(f)
      @sum = f
    end

    def each(&block)
      to_a.each &block
    end

    private

    def min
      @min || 1
    end

    def max
      @max
    end

    def to_a
      raise ArgumentError, "no upper bound" unless max
      squares.each_with_object([]) do |(b, bs), triplets|
        squares.take_while {|_, as| as < bs }.each do |a, as|
          if c = inverted_squares[as + bs]
            triplets << Triplet.new(a, b, c)
          end
        end
      end.select(&filter_block)
    end
    alias_method :to_ary, :to_a

    def squares
      @squares ||= Hash[range.map {|x| [x, x**2] }]
    end

    def inverted_squares
      @inverted_squares ||= Hash[squares.map(&:reverse)]
    end

    def range
      @range ||= (min..max)
    end

    def filter_block
      @sum ? ->(x){ x.sum == @sum } : ->(x){ x }
    end
  end
end
