class Raindrops
    SOUND = {
        3 => 'Pling',
        5 => 'Plang',
        7 => 'Plong'
    }

    def self.convert(num)
        primes = primes(num)
        if primes.include?(3) || primes.include?(5) || primes.include?(7)
            str = ''
            primes.each{ |p|
                unless SOUND[p] == nil
                    str << SOUND[p]
                end
            }
            return str
        end
        return num.to_s 
    end

    def self.primes(num)
        primes = []
        2.step(num, 1) { |i|
            if (num % i) > 0
                next
            end
            num /= i
            primes.push(i)
            redo
        }
        return primes.uniq
    end

end
