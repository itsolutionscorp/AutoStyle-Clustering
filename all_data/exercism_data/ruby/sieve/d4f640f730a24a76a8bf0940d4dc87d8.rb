class Sieve

  def initialize(num)
    @range = Array(2..num)
    @primes = []
  end

  def primes
    while @range.any?
      factor = @range.shift
      @range.delete_if { |elem| elem % factor == 0 }
      @primes << factor
    end
    @primes
  end
end
