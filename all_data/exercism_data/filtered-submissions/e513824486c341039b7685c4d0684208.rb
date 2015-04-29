def compute(seq_1,seq_2)
    hamm_dist = 0
    seq_1_length = seq_1.length
    seq_2_length = seq_2.length

    if seq_1_length != seq_2_length
      return 'Invalid input strings must be of equal length'
    else

      unless seq_2 == seq_1

        for char_pos in 0..(seq_1_length - 1)
          if seq_1[char_pos] != seq_2[char_pos]
            hamm_dist += 1
          end
        end
      end
    end

    return hamm_dist
  end