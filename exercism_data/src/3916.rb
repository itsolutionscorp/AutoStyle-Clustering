class Hamming

  def compute(seq_1,seq_2)
    hamm_dist = 0
    seq_1_length = seq_1.length
    seq_2_length = seq_2.length
    # Logic of the check first validates sequence lengths match
    if seq_1_length != seq_2_length
      return 'Invalid input strings must be of equal length'
    else
      #Quick check to see if the sequences match and just returns 0 if they do
      unless seq_2 == seq_1
        #Compares each character in the sequences to see if they match and increments hamm_dist when they dont
        for char_pos in 0..(seq_1_length - 1)
          if seq_1[char_pos] != seq_2[char_pos]
            hamm_dist += 1
          end
        end
      end
    end
    #Returns the hamming distance
    return hamm_dist
  end
end
