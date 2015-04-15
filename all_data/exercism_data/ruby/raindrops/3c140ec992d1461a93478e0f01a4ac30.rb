require 'prime'

class Raindrops
  REGEXP = /^[357]/
  VALUES = { '3' => 'Pling', '5' => 'Plang', '7' => 'Plong' }
  def self.convert(number)
    if (number % 3 == 0) || (number % 5 == 0) || (number % 7 == 0)
      primes = Prime.prime_division(number)
      primes.map do |factor|
        factor_str = factor[0].to_s
        factor_str.gsub(REGEXP, VALUES) if factor_str =~ REGEXP
      end.join
    else
      number.to_s
    end
  end
end
