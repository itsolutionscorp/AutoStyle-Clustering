class Raindrops
    require 'prime'

    RAINDROPS_TABLE = Hash.new do |hash, key|
        value = key.prime_division.map{|factor,exp| factor}
            .select{|n| [3,5,7].include? n}
            .sort
            .map{|n|
                case n
                    when 3 then "Pling"
                    when 5 then "Plang"
                    when 7 then "Plong"
                end }
        value.empty? ? key.to_s : value.join("")
    end

    def self.convert(num)
        RAINDROPS_TABLE[num]
    end
end
