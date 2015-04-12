class Hamming
def compute(first_strand, second_strand)
        distance = 0
        counter = 0
        while counter < first_strand.length && counter < second_strand.length
            if first_strand[counter] != second_strand[counter]
                difference += 1
                counter += 1
            else
                counter += 1
            end 
        end
    return distance
    end
end
