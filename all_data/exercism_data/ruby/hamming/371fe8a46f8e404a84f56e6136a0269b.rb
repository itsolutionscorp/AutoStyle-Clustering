class Hamming

  def self.compute(seq_1,seq_2)
    # Initialize hamming distance to 0
    hamm_dist = 0
    seq_1_length = seq_1.length
    seq_2_length = seq_2.length
    # Logic of the check first validates sequence lengths match
    if seq_1_length != seq_2_length
      fail 'Invalid input sequences must be of equal length'
    else
      # Quick check to see if the sequences match and just returns 0 if they do
      unless seq_2 == seq_1
        # Compares each character in the sequences to see if they match and increments hamm_dist when they dont
        seq_1.each_char.with_index do |char, index|
          if char != seq_2[index]
            hamm_dist += 1
          end
        end
      end
    end
    # Returns the hamming distance
    hamm_dist
  end
end
