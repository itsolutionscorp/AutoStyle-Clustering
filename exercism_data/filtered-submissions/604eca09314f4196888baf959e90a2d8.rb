class Hamming

  def compute(strand1, strand2)
    pairs = strand1.chars.zip(strand2.chars)
    pairs.count { |old_base,new_base| new_base && old_base != new_base }
  end

end
