class Hamming
  def compute(strand1, strand2)
    mismatch = 0

    strand1_chars = strand1.split""
    strand2_chars = strand2.split""

    while one = strand1_chars.shift
      break if strand2_chars.empty?
      mismatch += 1 if one != strand2_chars.shift
    end

    mismatch
  end
end
