class Raindrops
  
  def primes_of(x)
    primes = []
    prime = x
	(2..Math.sqrt(x).to_i).each do |i|
	  break if prime <= i
	  while (prime > i && prime % i == 0)
	    prime /= i
		primes.push(i)
	  end
	end
	primes.push(prime)
	return primes
  end
  
  def self.convert(x)
    result_string = String.new
    prime_factors = new.primes_of(x)
	translation = [[3,'Pling'],[5,'Plang'],[7,'Plong']]
	translation.each do|pair|
	  if prime_factors.include?(pair[0]) then result_string += pair[1] end
	end
	unless (prime_factors & [3,5,7]).any? then result_string = x.to_s end
    return result_string
  end
  
end
