class Raindrops

    # Converts prime factorsof a number to a word
    # @param num [int]
    def self.convert(num)
        conversion = ''
        pf = prime_factorize(num)
        if pf.include?(3) then
            conversion += 'Pling'
        end
        if pf.include?(5) then
            conversion += 'Plang'
        end
        if pf.include?(7) then
            conversion += 'Plong'
        end

        if conversion == ''
            return num.to_s
        end
        conversion

                

    end

    # gets the prime factorization of a number
    # @param num [int]
    def self.prime_factorize(num)
        curr = num
        prime_facts = []
        until is_prime(curr) || curr < 2
            get_primes_upto(curr).each { |n| 
                if curr%n == 0 then
                    prime_facts.push(n)
                    curr = curr/n   
                    break
                end
            }
        end
        prime_facts.push(curr)
        prime_facts.sort

    end

    # get a list of prime numbers up to a number
    # # @param num [int]
    def self.get_primes_upto(num)
        primes = []
        for i in 0..num
            if is_prime(i) then
                primes.push(i)
            end
        end
        primes
    end

    # determines if a number is prime or not
    # @param num [int]
    def self.is_prime(num)
        if num == 0 or num == 1 then
            return false
        end

        for i in 2..(num-1) 
            if num % i == 0 then
                return false
            end
        end
        true
    end

end

puts Raindrops.convert(1)
