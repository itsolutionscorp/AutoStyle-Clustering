require "prime"
module PrimeFactors
  def self.for n
    Prime.prime_division(n).each_with_object([]) do |(p, e), memo|
      e.times { memo << p}
    end
  end
end
