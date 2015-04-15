require "prime"

class Raindrops
  class << self
    def convert(n)
      pling_plongs = convert_primes(n)

      return n.to_s if pling_plongs.empty?
      return pling_plongs
    end

  private
    def convert_primes(n)
      prime_factors(n).map { |n| conversions[n] }.compact.join("")
    end

    def conversions
      {
        3 => "Pling",
        5 => "Plang",
        7 => "Plong"
      }
    end

    def prime_factors(n)
      n.prime_division.flatten.uniq
    end
  end
end
