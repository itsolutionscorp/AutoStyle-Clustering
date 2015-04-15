class Hamming

  def self.compute(strand_a, strand_b)
    # we felt this corner case should be caught
    raise "Strands are different lengths!" if strand_a.length != strand_b.length

    hamming_distance = 0

    strand_a.length.times do |position|
      if strand_a[position] == strand_b[position]
        # do nothing
      else
        hamming_distance = hamming_distance + 1
      end
    end

    hamming_distance
  end

end
