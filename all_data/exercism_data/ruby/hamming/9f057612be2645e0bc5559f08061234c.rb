class Hamming
  # Public: Computes the Hamming distance between two strings.
  #
  # For two strings that are the same length, the hamming distance is
  # defined as the number of positions where the corresponding
  # characters are different.
  # 
  # first_string - The first String to compare
  # second_string - The second String to compare
  #
  # Returns the Integer hamming distance between the two strings
  def self.compute(first_string,second_string)
    self.compute_sequences(first_string.each_char,second_string.each_char)
  end

  # Public: Computes the Hamming disance between the contents of two
  # enumerators.
  #
  # For two enumerators that iterate over the same number of elements,
  # the Hamming distance is defined as the number of positions where the
  # corresponding characters are different.
  #
  # first - First Enumerator to compare
  # second - Second Enumerator to compare
  #
  # Returns the Integer hamming distance between the two strings.
  def self.compute_sequences(first, second)
    distance_so_far = 0
    begin
      loop do
        first_char = first.next
        second_char = second.next
        distance_so_far += 1 if first_char != second_char
      end
    rescue StopIteration
      # Expected when end of the enumeration is reached
    end
    distance_so_far
  end
end
