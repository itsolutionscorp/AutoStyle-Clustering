class Hamming

  # Computes the Hamming distance between two arbitrary strings.
  #
  # first - First string to compare
  # second - Second string to compare
  #
  # Returns integer of the hamming distance.
  def compute(first, second)
    dist = 0
    first.chars.each_index do |i|
      dist += first[i] == second[i] ? 0 : 1
    end
    return dist
  end

end
