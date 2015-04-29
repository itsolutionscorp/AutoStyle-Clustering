class PrimeFactors

  class << self

    def for n
      @n, @primes = n, []

      factors_of_2
      factors_of_odd

      @primes
    end

    private

    def factors_of_2
      while @n % 2 == 0
        @primes << 2
        @n /= 2
      end
    end

    def factors_of_odd
      (3..Math.sqrt(@n)).select{|x| x.odd? }.each do |i|
        while @n % i == 0
          @primes << i
          @n /= i
        end
      end
    
      check_for_end_prime
    end

    def check_for_end_prime
      @primes << @n if @n > 2
    end

  end

end
