module Raindrops

  def self.convert(num)
    String.new.tap do |raindrops|
      primes = prime_factors(num)
      raindrops << 'Pling' if primes.include? 3
      raindrops << 'Plang' if primes.include? 5
      raindrops << 'Plong' if primes.include? 7
      return num.to_s if raindrops.empty?
    end
  end

  def self.prime_factors(num)
    Array.new.tap do |prime_factors|
      div_num = 2

      while div_num < num
        if num % div_num == 0
          prime_factors << div_num
          num = num / div_num
        else
          div_num += 1
        end
      end
      prime_factors << div_num
    end
  end
end
