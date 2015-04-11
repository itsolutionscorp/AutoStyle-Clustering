class Hamming

  def self.compute(dna1, dna2)
    @dna1, @dna2 = dna1, dna2
    set_strand_position_by_length(dna1, dna2) unless strands_are_equal?
    dna1_strand, dna2_strand = @dna1.scan(/\w/), @dna2.scan(/\w/)

    count_the_distance(dna1_strand, dna2_strand)
  end

private
  def self.count_the_distance(dna1_strand, dna2_strand)
    count = 0
    dna1_strand.length.times do |i|
      count += 1 unless dna1_strand[i] == dna2_strand[i]
    end

    count
  end

  def self.set_strand_position_by_length(dna1, dna2)
    @dna1, @dna2 = dna1.length < dna2.length ? [dna1, dna2] : [dna2, dna1]
  end

  def self.strands_are_equal?
    @dna1.length == @dna2.length
  end

end
