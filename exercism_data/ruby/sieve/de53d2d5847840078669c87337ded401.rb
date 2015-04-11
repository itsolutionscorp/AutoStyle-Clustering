class Sieve
  def initialize(n)
    range = Array[*(2..n)]
    trues = [true].cycle.take(range.size)
    @range = Hash[range.zip(trues)]
  end

  def primes
    n = (@range.keys.last**0.5).ceil

    @range.keys.take(n).each do |k|
      (k+1).upto(@range.keys.last) do |x|
        @range[x] = false if (x % k).zero? && @range[x]
      end
    end

    @range.select { |_, v| v }.keys
  end
end
