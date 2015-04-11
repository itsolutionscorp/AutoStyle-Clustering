require 'prime'

class Raindrops
    DROP_TALK = {
        3 => 'Pling',
        5 => 'Plang',
        7 => 'Plong'
    }

    def self.convert(int)
        list = Prime.prime_division(int).flatten
            .map {|num| DROP_TALK[num]}.compact.join

        list.empty? ? int.to_s : list
    end
end
