class DNA
  def initialize(strand_string)
    @strand = string_to_strand(strand_string)
  end

  def hamming_distance(strand_string)
    strand1, strand2 = trim_strands_to_equal_length(@strand, string_to_strand(strand_string))
    calculate_distance(strand1, strand2)
  end

  private 
  def string_to_strand(input)
    input.split('')
  end

  def trim_strands_to_equal_length(s1, s2)
    min_length = [s1.size, s2.size].min - 1
    [(s1)[0..min_length], (s2)[0..min_length]]
  end

  def calculate_distance(strand1, strand2)
    strand1.zip(strand2).reject { |n| n[0] == n[1]}.size
  end
end
