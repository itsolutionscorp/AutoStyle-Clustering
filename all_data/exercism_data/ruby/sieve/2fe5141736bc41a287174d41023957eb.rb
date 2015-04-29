class Sieve
  attr_accessor :limit, :range

  def initialize(num)
    @limit = num
  end

  def primes
    @range = (2..@limit).to_a

    for i in 2..Math.sqrt(@limit).ceil
      for j in i..@limit
        if @range[j - 1] != nil && @range[j - 1] % i == 0 && @range[j - 1] != i
          @range[j - 1] = nil
        end
      end
    end
    @range.compact
  end
end
