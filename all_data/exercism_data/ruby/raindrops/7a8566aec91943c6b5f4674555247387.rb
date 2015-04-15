class Raindrops
    def self.convert num
        primes = convert_to_primes(num)
        rain_string = ""
        primes.each do |prime|
            case prime
            when 3
                rain_string << "Pling"
            when 5
                rain_string << "Plang"
            when 7
                rain_string << "Plong"
            end
        end
        rain_string.empty? ? num.to_s : rain_string
    end

    def self.convert_to_primes num
        prime_array = [num]
        return prime_array unless num > 3
        i = 2;
        (num-2).times do
            prime_array << i if (num % i) == 0
            i += 1
        end
        prime_array

    end
end
