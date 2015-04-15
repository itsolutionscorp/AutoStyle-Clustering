class Hamming
    def self.compute(first,second)
        if first.length == second.length
            #puts "Same Length, computing"
            num_of_differences = 0
            (0..first.length).each do |position|
                if first[position] != second[position]
                    num_of_differences += 1
                end
            end
            return num_of_differences
        else
            puts "Not same length, cancelling"
        end
    end
end
