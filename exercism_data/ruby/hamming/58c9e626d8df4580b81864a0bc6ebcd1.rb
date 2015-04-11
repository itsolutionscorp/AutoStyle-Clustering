class Hamming

  def self.compute(strand_1, strand_2)
    diff = 0
    shorter_strand = 'TBD'
    longer_strand = 'TBD'

    if strand_1.length > strand_2.length
      shorter_strand = strand_2.split('')
      longer_strand = strand_1.split('')
    else
      shorter_strand = strand_1.split('')
      longer_strand = strand_2.split('')
    end

    shorter_strand.each_with_index do |nucleotide, index|
      if nucleotide != longer_strand[index]
        diff += 1
      end   
    end

    diff
  end

end
