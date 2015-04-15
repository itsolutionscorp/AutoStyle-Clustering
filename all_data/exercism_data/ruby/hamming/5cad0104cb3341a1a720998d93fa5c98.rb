class Hamming
    def self.compute( arg1, arg2 )
        @i = 0
        @ham = 0
        while @i < arg1.length || @i < arg2.length
            if arg1[@i] != arg2[@i]
                @ham += 1
            end
            @i+=1
        end
        return @ham
    end
end
