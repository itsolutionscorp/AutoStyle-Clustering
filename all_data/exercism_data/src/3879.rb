def compute(strand1, strand2)
    shortest_strand, other_strand = [strand1, strand2].sort_by(&:length)

    shortest_strand.chars.zip(other_strand.chars).count do |acid1, acid2|
      acid2 != acid1
    end
  end