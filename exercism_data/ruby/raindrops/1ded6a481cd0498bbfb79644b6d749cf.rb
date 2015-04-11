# I misread the brief at first and wrote a prime number sieve
# I've decided to leave it in here because it worked!
#
#max = 12121
#    
#def prime_sieve(highest_number)
#
#    def sieve_iter(remaining, prime_numbers, stop_at)
#        new_prime = remaining.shift
#        if new_prime < stop_at
#            prime_numbers.push(new_prime)
#
#            new_array = remaining.select { |num|
#                num % new_prime != 0    
#            }
#
#            sieve_iter(new_array, prime_numbers, stop_at)
#        else
#            prime_numbers.concat(remaining)
#        end
#    end
#    
#    sieve_iter((2..highest_number).to_a, [], Math.sqrt(highest_number)) 
# end
#
#prime_array = prime_sieve(max)

def map_hash(string_hash, number)
	string_hash.map do |index, string| 
    	if number % index == 0
    		string
    	end
    end
end

class Raindrops

    def self.convert(number)
    	string_hash = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

    	mapped_hash = map_hash(string_hash, number).join

    	if mapped_hash.length > 0
    		mapped_hash
    	else
    		number.to_s
    	end

    end

end
