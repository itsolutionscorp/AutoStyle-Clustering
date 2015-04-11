require 'prime'

class Raindrops
  CONVERSION = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    primes = Prime.prime_division(number)
    string = primes.map { |prime, _| CONVERSION[prime] }.join

    if string.empty?
      number.to_s
    else
      string
    end
  end
end
