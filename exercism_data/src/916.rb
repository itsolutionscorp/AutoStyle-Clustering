def compute(seq_1,seq_2)

    hamm_dist = 0
    seq_1_length = seq_1.length
    seq_2_length = seq_2.length

    if seq_1_length != seq_2_length
      fail 'Invalid input sequences must be of equal length'
    else

      unless seq_2 == seq_1

        seq_1.each_char.with_index do |char, index|
          if char != seq_2[index]
            hamm_dist += 1
          end
        end
      end
    end

    hamm_dist
  end