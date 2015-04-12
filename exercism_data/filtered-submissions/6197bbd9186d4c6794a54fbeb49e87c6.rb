def compute(strand1, strand2)

    # Initialize the distance variable
    distance = 0

    # Step through each character in the two strings and compare them
    for i in 0...[strand1.length, strand2.length].min

      # If the characters do not match, increment the distance variable
      if strand1[i] != strand2[i]
        distance += 1
      end
    end

    # Return the number of differences found between the two strings
    return distance
  end