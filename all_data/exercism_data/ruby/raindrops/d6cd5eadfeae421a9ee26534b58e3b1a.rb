class Raindrops

  RAINDROPS = {
      '3' => 'Pling',
      '5' => 'Plang',
      '7' => 'Plong'
  }
  def self.convert(prime_num)
    if prime_num == 1
      return prime_num.to_s
      else
        primes = find_prime_factors(prime_num)
      end
    drops = primes.map { |p| RAINDROPS[p.to_s] }.join

    drops.empty? ? prime_num.to_s : drops
  end

  def self.find_prime_factors(num)
    primes = []
    (2..num).each do |n|
      if(num % n == 0)
        primes << n if is_prime?(n)
      end
    end
    primes
  end

  def self.is_prime?(num)
    (2..(Math.sqrt(num).ceil)).each do |n|
      return false if (num % n == 0)
    end
  end

end
