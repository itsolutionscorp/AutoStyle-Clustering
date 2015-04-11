require 'benchmark'

module Prime
  class << self
    def nth(n)
      raise ArgumentError if n <= 0
      prime = 2
      i = 2
      until n == 1
        if is_prime?(i)
          prime = i
          n -= 1
        end
        i +=1
      end
      prime
    end

    def is_prime?(n)
      return false if n.even?
      i = 3
      until i >= n-1
        return false if n % i == 0 
        i += 2
      end
      true
    end
  end
end

puts Benchmark.measure { Prime.nth(10001) }
