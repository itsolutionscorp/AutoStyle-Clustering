class Hamming
  def self.compute(first_strand, other_strand)
    first_strand = standardize_first_strand_length(first_strand, other_strand)
    diff = 0
    first_array  = first_strand.split("")
    other_array = other_strand.split("")
    first_array.each_with_index do |char, i|
      diff += 1 if char != other_array[i]
    end
    diff
  end

  private

  def self.standardize_first_strand_length(first_strand, other_strand)
    if first_strand.length > other_strand.length
      first_strand = first_strand[0..other_strand.length - 1]
    end
    first_strand
  end
end
