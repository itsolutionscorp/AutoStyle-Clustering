#!/usr/bin/env ruby


class Raindrops
    def prime_factors(number)
        primes = []
        prime = 2
        while number > 1 do
            if number % prime == 0
                number /= prime
                primes.push(prime)
            else
                prime += 1
            end
        end
        primes
    end
    
    def convert(number)
        primes = prime_factors(number)
        result = ''
        result += 'Pling' if primes.member? 3
        result += 'Plang' if primes.member? 5
        result += 'Plong' if primes.member? 7
        if result == ''
            number.to_s
        else
            result
        end
    end
end
