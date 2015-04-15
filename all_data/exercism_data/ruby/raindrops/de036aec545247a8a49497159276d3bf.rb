class Raindrops

  require 'prime'

  def self.convert(num)
    primes = num.prime_division.collect {|f| f[0] }
    str = ''
    str << 'Pling' if primes.include? 3
    str << 'Plang' if primes.include? 5
    str << 'Plong' if primes.include? 7
    str.empty? ? num.to_s : str
  end

end
