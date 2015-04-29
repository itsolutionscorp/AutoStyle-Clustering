class Raindrops
  PRIMES = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(num)
    primes = []

    PRIMES.keys.sort.each do |prime|
      primes.push prime if num % prime == 0
    end

    return num.to_s if primes.count == 0
    return primes.map{|p| PRIMES[p]}.join('')
  end
end
