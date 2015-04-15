class Raindrops
    def self.convert (number)
        prime_sounds = {
            3 => 'Pling',
            5 => 'Plang',
            7 => 'Plong',
        }

        sound = prime_sounds.each_with_object('') { |(prime, prime_sound), string| string << prime_sound if number % prime == 0 }

        sound.empty? ? number.to_s : sound
    end
end
