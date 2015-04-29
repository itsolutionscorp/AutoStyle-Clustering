
class Raindrops
    def self.convert(number)
        @words = ''
        
        drop(number, 3, 'Pling')
        drop(number, 5, 'Plang')
        drop(number, 7, 'Plong')
        
        if @words == ''
            return number.to_s
        else
            return @words
        end
    end

    def self.drop(number, factor, word)
        base_n_string = number.to_s(factor)
        if base_n_string[base_n_string.size - 1] == '0'
            @words += word
        end
    end
end
