def compute(first_sequence, second_sequence)
    hamming_counter = 0
    unless first_sequence.length != second_sequence.length
      if first_sequence == second_sequence
        hamming_counter
      else
        split_first_sequence = first_sequence.split('')
        split_second_sequence = second_sequence.split('')
        split_first_sequence.select.with_index do |first_sequence_base,index|
          hamming_counter += 1 if first_sequence_base != split_second_sequence[index]
        end
         hamming_counter
      end
    end
  end