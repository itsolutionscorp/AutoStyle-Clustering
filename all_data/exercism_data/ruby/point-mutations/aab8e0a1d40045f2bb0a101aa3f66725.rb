class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(sequence)
    if sequence.length < @strand.length
      calculate_distance(sequence,@strand)
    else
      calculate_distance(@strand,sequence)
    end
  end

  private

  def calculate_distance(short_strand, long_strand)
    count=0
    short_strand.chars.each_index do |index|
      if short_strand[index]!=long_strand[index]
        count+=1
      end
    end
    count
  end

end
