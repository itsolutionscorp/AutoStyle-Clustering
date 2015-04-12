class Hamming
  def compute(first_strand, second_strand)
    min_length = [first_strand.length, second_strand.length].min-1
    zipped_strands = first_strand.chars.zip(second_strand.chars)
    zipped_strands[0..min_length].count{|x,y| x != y}
  end
end
