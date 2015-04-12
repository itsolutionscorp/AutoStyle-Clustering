class Hamming
  def compute(strand1, strand2)
    len, diff = 0, 0
    strand1.each_char do |c|
      diff += 1 if (c != strand2[len])
      len = len + 1
    end
    return diff
  end
end
