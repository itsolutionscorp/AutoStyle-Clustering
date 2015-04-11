class Raindrops
    SOUND = {
        3 => 'Pling',
        5 => 'Plang',
        7 => 'Plong'
    }

    def self.convert(num)
        drops = SOUND.keys.map { |prime| 
            num % prime == 0 ? SOUND[prime] : ""
        }.reduce(:+)

        drops.empty?  ? num.to_s : drops 
    
    end

end
