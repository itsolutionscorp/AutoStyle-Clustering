def compute(strand, other_strand)
    min_length = [strand.length, other_strand.length].min
    strand = strand[0...min_length]
    other_strand = other_strand[0...min_length]

    count = 0
    min_length.times do |i|
      strand_char = strand[i]
      other_char = other_strand[i]
      count += 1 unless strand_char == other_char
    end

    return count
  end