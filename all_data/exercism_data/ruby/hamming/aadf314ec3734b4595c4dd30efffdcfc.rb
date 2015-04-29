class Hamming
  def self.compute(a, b)
    calc = DistanceCalculator.new(a,b)
    calc.distance
  end

  class DistanceCalculator
    attr_reader :a, :b

    def initialize(a,b)
      @a, @b = a.chars, b.chars
    end

    def comparable_length
      [@a.length, @b.length].min
    end

    def comparison
      a.zip(b).map { |x, y| x == y }.first(comparable_length)
    end

    def distance
      comparison.inject(0) do |distance, match|
        if match
          distance
        else
          distance += 1
        end
      end
    end
  end
end
