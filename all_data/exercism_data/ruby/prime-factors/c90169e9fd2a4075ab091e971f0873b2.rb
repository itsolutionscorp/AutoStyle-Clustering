class PrimeFactors
  def self.for(n)
    (2..Math.sqrt(n)).each_with_object([]) { |x, factors|
      while n % x == 0
        n /= x
        factors << x
      end
    }.tap { |factors| factors << n unless n == 1 }
  end
end
