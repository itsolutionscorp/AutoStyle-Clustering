class Palindromes
  def initialize(hash)
    @max = hash[:max_factor]
    @min = hash[:min_factor]
    @min = 1 if @min.nil?
    @factor = Struct.new(:value, :factors)
  end

  def generate
    @maxp, @minp = 0, 1_000_000_000
    @maxp_factors, @minp_factors = [], []

    @min.upto(@max) do |i|
      i.upto(@max) do |j|
        m = i * j

        if palindrome?(m)
          if m >= @maxp
            if m > @maxp
              @maxp = m
              @maxp_factors = [[i, j]]
            else
              @maxp = m
              @maxp_factors << [i, j]
            end
          end

          if m <= @minp
            if m < @minp
              @minp = m
              @minp_factors = [[i, j]]
            else
              @minp = m
              @minp_factors << [i, j]
            end
          end
        end
      end
    end
  end

  def largest
    @factor.new(@maxp, @maxp_factors)
  end

  def smallest
    @factor.new(@minp, @minp_factors)
  end

  private

  def palindrome?(n)
    s = n.to_s
    s == s.reverse
  end
end
