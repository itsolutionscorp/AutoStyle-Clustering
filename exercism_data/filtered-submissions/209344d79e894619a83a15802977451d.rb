class Hamming
    def compute(first_strand, second_strand)
        @pom = 0
        @first_length = first_strand.length
        @second_length = second_strand.length
        @min_length = [@first_length, @second_length].min
        for i in 0..(@min_length-1)
            (@pom += 1) unless first_strand.chars[i] == second_strand.chars[i]
        end
        @pom
    end
end
