require 'prime'

module Raindrops
  class << self

    def convert(number)
      primes = []
      Prime.each(number) {|prime| primes << prime }

      if !primes.include?(3) && !primes.include?(5) && !primes.include?(7)
        raindrops = number.to_s
      else
        raindrops = ''
        raindrops += 'Pling' if primes.include?(3)
        raindrops += 'Plang' if primes.include?(5)
        raindrops += 'Plong' if primes.include?(7)
      end
      
      return raindrops
    end

  end
end
