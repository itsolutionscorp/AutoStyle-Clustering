class Hamming

  # Computes the Hamming distance between two arbitrary strings.
  #
  # first - First string to compare
  # second - Second string to compare
  #
  # Returns integer of the hamming distance.
  def self.compute(first, second)
    dist = 0
    for i in 0..first.length
      dist += first[i] == second[i] ? 0 : 1
    end
    return dist
  end

end
