class Hamming
  def compute(strand_a, strand_b)
    difference = 0

    if strand_a.length > strand_b.length
      strand_a = strand_a[0, strand_b.length]
    end

    strand_a.split("").zip(strand_b.split("")).compact.each do |l, r|
      unless l == r
        difference += 1
      end
    end

    difference
  end
end
