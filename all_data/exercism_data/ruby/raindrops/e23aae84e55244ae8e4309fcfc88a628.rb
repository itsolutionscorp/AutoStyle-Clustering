class Raindrops
  class << self
    def convert(number)
      matching = find_matching_factors(number.factors)

      if matching.any?
        matching.map { |n| primes_map[n] }.join('')
      else
        number.to_s
      end
    end

    def primes_map
      {
        3 => "Pling",
        5 => "Plang",
        7 => "Plong"
      }
    end

    def primes
      primes_map.keys
    end

    def find_matching_factors(factors)
      primes & factors
    end
  end
end

class Integer
  def factors
    limit = self / 2

    (2..limit).each_with_object([]) do |n, result|
      if self % n == 0
        result << n
        result += (self / n).factors
      end
    end + [ self ]
  end
end
