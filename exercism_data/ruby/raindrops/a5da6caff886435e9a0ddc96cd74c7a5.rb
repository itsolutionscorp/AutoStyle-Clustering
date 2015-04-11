require 'prime'

module Raindrops
  class << self
    def convert(num)
      factors = prime_factors(n).select { |n| [3,5,7].include?(n) }
      return num.to_s if factors.empty?
      plingify(factors.join(""))
    end

    private

    def prime_factors(num)
      Prime.each(num).collect { |prime| prime if num % prime == 0 }.compact
    end

    def plingify(string)
      string.gsub(/[357]/, "3" => "Pling", "5" => "Plang", "7" => "Plong")
    end
  end
end
