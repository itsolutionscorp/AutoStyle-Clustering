class Raindrops

  require 'prime'
  RAINDROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(num)
    primes = num.prime_division.collect {|f| f[0] }
    str = ''
    RAINDROPS.each do |key,raindrop|
      str << raindrop if primes.include? key
    end
    str.empty? ? num.to_s : str
  end

end
