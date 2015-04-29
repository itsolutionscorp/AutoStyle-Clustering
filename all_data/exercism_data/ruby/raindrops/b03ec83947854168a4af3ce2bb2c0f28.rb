require 'prime'

module Raindrops
  class << self
    def convert(n)
      converted = convert_prime_factors(n)
      converted.empty? ? n.to_s : converted
    end

    private

    def convert_prime_factors(n)
      interesting_prime_factors(n).map do |factor|
        case factor
        when 3 then 'Pling'
        when 5 then 'Plang'
        when 7 then 'Plong'
        end
      end.join
    end

    def interesting_prime_factors(n)
      return [1] if n == 1
      Prime.prime_division(n).
        map(&:first).
        keep_if { |f| [3, 5, 7].include? f }
    end
  end
end
