def compute(first_strand, second_strand) # static method, not instance method
        first_strand_arr = first_strand.split('')
        second_strand_arr = second_strand.split('')

        num_mutations = 0
        first_strand_arr.each_index do |i|
          if (i < second_strand_arr.size && first_strand_arr[i] != second_strand_arr[i])
            num_mutations += 1
          end
        end

        num_mutations
    end