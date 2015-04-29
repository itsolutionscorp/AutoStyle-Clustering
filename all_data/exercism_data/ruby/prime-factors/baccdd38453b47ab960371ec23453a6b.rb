class PrimeFactors
  def self.for(number, algorithm = Trail)
    algorithm.prime_factors(number)
  end

  module Trail
    def self.prime_factors(number)
      n = number
      (2..number).each_with_object([]) do |current, factors|
        if n == 0
          break
        elsif n % current == 0
          factors << current
          n /= current
          redo
        else
          next
        end
      end
    end
  end

  module StdLib
    require 'prime'
    def self.prime_factors(number)
      Prime.prime_division(number).map { |f,c| [f]*c }.flatten
    end
  end

  # Source: https://en.wikipedia.org/wiki/Fermat%27s_factorization_method
  module Fermat
    def self.prime_factors(number)
      x = Math.sqrt(number).ceil
      r = x**2 - number
      root_of_r = Math.sqrt(r)
      while root_of_r - root_of_r.to_i != 0
        r = x**2 - number
        x += 1
      end
      a = x + root_of_r
      b = x - root_of_r
      [a, b]
    end
  end

  module PollardsRho
    def self.prime_factors(n)
      x = 2
      y = 2
      d = 1

      while d == 1
        x = f(x, n)
        y = f(f(y, n), n)
        d = (x-y).abs.gcd(n)
      end

      raise "Error" if d == n
      d
    end

    def self.f(x, n)
      (x**2 + 1) % n
    end
  end

  module EratosthenesSieve
    def self.prime_factors(n)
      lower_bound = 2
      upper_bound = n
      sqrt_upper = Math.sqrt(upper_bound).round
      used_numbers = Array.new(upper_bound) { |e| e =  false }
      primes = []

      (2..sqrt_upper).each do |m|
        unless used_numbers[m]
          primes << m if m >= lower_bound

          k = m * m
          while k <= upper_bound
            used_numbers[k] = true
            k += m
          end

        end
      end

      (sqrt_upper+1..upper_bound).each do |n|
        if !used_numbers[n] && n >= lower_bound
          primes << n
        end
      end
      primes
    end
  end
end
