class Hamming
  def compute(strand1, strand2)
    difference = 0
    lengths = (strand1.length - strand2.length)
    difference += (0..strand1.length - 1).count do |position|
      strand1[position] != strand2[position]
    end
    if lengths < 0
      difference
    else
      difference - lengths.abs
    end
  end
end
