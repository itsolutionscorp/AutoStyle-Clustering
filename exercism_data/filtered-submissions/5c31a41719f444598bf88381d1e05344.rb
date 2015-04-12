def compute(first_sequence, second_sequence)
    hamming_counter = 0
    unless first_sequence.length != second_sequence.length
      if first_sequence === second_sequence
        hamming_counter
      else
        split_first_sequence = first_sequence.split('')
        split_second_sequence = second_sequence.split('')
        counter = split_first_sequence.length
        while  counter > 0
          counter -= 1
          guard = split_first_sequence.pop
          guard_second = split_second_sequence.pop
          if guard != guard_second
            hamming_counter += 1
          end
        end
         hamming_counter
      end
    end
  end