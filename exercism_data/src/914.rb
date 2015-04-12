class Hamming
  def compute(strand_a, strand_b)
    distance = []

    length = [strand_a.length, strand_b.length].min

    strand_a = strand_a.split("").take(length)
    strand_b = strand_b.split("").take(length)

    strand_b.each_with_index do |nuc, index|
      if nuc != strand_a[index]
        distance << nuc
      end
    end
    distance.count
  end
end
