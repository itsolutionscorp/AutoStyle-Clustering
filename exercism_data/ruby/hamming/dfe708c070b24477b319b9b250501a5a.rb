class Hamming
  def self.compute(strand_a, strand_b)
    length = [strand_a.length, strand_b.length].min
    (0...length).inject(0) do |mutations, index|
      strand_a[index] != strand_b[index] ? mutations + 1 : mutations
    end
  end
end
