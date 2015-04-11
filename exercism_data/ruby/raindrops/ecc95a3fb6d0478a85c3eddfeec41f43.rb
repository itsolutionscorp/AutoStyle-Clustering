class Raindrops
  def convert(number)
  	conversion = ""
  	drops = [3,5,7]
  	prime_factors = primes_for(number)
  	prime_factors.sort!.uniq!
  	prime_factors.each do |prime|
      if prime == 3
      	conversion = conversion + "Pling"
      
      elsif prime == 5
      	conversion = conversion + "Plang"
     
      elsif prime == 7
      	conversion = conversion + "Plong"
      
    
    end
    
  	end
    drop_exists = false
    drops.each do |drop|
      if prime_factors.include? drop
        drop_exists = true
      end
    end
    unless drop_exists
      conversion = number.to_s
    end
  	return conversion
  end

  def primes_for(number)
		prime_factors = []
		num = 2
		temp = number
		while num <= number
			if number % num == 0
				prime_factors.push num
				number = number/num
			else
				num = num + 1
			end

		end
		return prime_factors.pop temp
	end
end
