class Hamming

  def self.compute(strand_a, strand_b)
    return 0 if strand_a.length == 0 || strand_b.length == 0
    min_length =  [strand_a.length, strand_b.length].min - 1
    (0..min_length).inject(0) do |diff_count, i|
      strand_a[i] != strand_b[i] ? diff_count + 1 : diff_count
    end
  end
end
