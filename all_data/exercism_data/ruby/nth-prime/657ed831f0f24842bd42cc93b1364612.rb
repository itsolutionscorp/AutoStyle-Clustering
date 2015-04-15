 
 
 class Prime
    MAX_SIZE = 200000
    
    def self.nth(index)
        raise ArgumentError, 'index must be greater than 0.' if index < 1
        primes = sieve
        primes[index - 1]
    end
    
    def self.sieve
        sieve_array = Array.new(MAX_SIZE)
        sieve_array.each_index  do |index|
            sieve_array[index] = index
        end
        sieve_array[1] = 0
        
        sieve_array.each_index do |index|
            next if sieve_array[index] == 0

            step = index * 2
            while step < MAX_SIZE do 
                sieve_array[step] = 0
                step += index
            end
        end

        primes = Array.new()
        sieve_array.each do |value|
            primes.push(value) if value != 0
        end
        
        primes
    end
 end
