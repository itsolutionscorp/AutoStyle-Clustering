def compute(strand1, strand2)
    min_strand_length = [strand1.length, strand2.length].min
    strand1_nucleotides = strand1[0...min_strand_length].chars
    #distance = 0
    #strand1_nucleotides.each_with_index do |nucleotide, index|
    (0...min_strand_length).inject(0) do |distance, index|
      if strand1[index] != strand2[index]
        distance + 1
      else
        distance
      end
    end
  end