# Class to compute the Hamming distance between two DNA sequences
class Hamming

  # The compute method takes two strings as input, and returns an integer value
  def Hamming.compute(strand1, strand2)

    # Find the length of the shorter sequence
    if strand1.length > strand2.length
      sequence_length = strand2.length
    else
      sequence_length = strand1.length
    end

    # Initialize the distance variable
    distance = 0

    # Step through each character in the two strings and compare them
    for i in 0..sequence_length - 1

      # If the characters do not match, increment the distance variable
      if strand1[i] != strand2[i]
        distance += 1
      end
    end

    # Return the number of differences found between the two strings
    return distance
  end
end
