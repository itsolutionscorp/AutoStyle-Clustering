class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(sequence)
    calculate_distance([@strand,sequence].sort_by{|molecule|molecule.length})
  end

  private

  def calculate_distance(strands)
    count=0
    strands[0].chars.each_index do |index|
      if strands[0][index]!=strands[1][index]
        count+=1
      end
    end
    count
  end

end
