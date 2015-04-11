class Prime
  class << self
    def nth(n)
      raise ArgumentError.new('Expected a positive integer') unless n && n.integer? && n > 0
      generate_primes
      while @primes.length < n
        next_max_num = [100,@primes.last*1.2].max.to_i
        generate_primes(next_max_num)
      end
      @primes[n-1]
    end

    def is_prime?(n)
      not 2.upto(n-1).detect { |num| n % num == 0 }
    end

    private

    def generate_primes(max=101)
      puts max
      @primes ||= [2]
      return @primes if @primes.last > max

      potentials = @primes.last.upto(max).to_a
      @primes.each { |prime| potentials.reject! { |num| num % prime == 0 } }
      potentials.reject! { |num| !is_prime?(num) }
      @primes += potentials
    end
  end
end
