class DNA
  attr :strand

  def initialize(strand)
    @strand = strand
  end

  # Ugly.
  def hamming_distance(other)
    distance = 0
    other_chars = other.chars
    @strand.chars.each_with_index do |char, index|
      other_char = other_chars[index] or break
      distance += 1 if char != other_char
    end

    distance
  end
end
