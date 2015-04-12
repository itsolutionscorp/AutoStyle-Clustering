class Hamming
  def compute(a, b)
    @hamming_distance = 0
    if a.length == b.length
      a.length.times do |i|
        @hamming_distance += 1 if a[i] != b[i]
      end
    else
      'cannot determine hamming distance of difference length strands'
    end
    @hamming_distance
  end
end
