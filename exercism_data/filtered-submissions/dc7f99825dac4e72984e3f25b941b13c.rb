class Hamming

  def compute(strand_1, strand_2)
    raise 'both strands must be of equal length' unless strand_1.length == strand_2.length
    strand_1_chars = strand_1.split('')
    strand_2_chars = strand_2.split('')
    diff = strand_1_chars.reject.with_index { |char, index| char == strand_2_chars[index]}
    diff.length
  end

end
