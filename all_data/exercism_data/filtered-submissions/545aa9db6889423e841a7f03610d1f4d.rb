def compute(first_strand, second_strand)
        @pom = 0
        @first_length = first_strand.length
        @second_length = second_strand.length
        if @first_length >= @second_length
            for i in 0..(@second_length-1)
                (@pom += 1) unless first_strand.chars[i] == second_strand.chars[i]
            end
        else
            for i in 0..(@first_length-1)
                (@pom += 1) unless first_strand.chars[i] == second_strand.chars[i]
            end
        end
        return @pom
    end