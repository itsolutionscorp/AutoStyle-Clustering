class Raindrops
  
  PRIMES_AND_DROPS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def convert(number)
    result = PRIMES_AND_DROPS.keys.sort.each_with_object('') do |prime,result| 
      result << PRIMES_AND_DROPS[prime] if prime_factor?(number, prime)
    end
    result.empty? ? number.to_s : result
  end

  private

  def prime_factor?(number, prime_factor)
    number % prime_factor == 0  
  end

end
