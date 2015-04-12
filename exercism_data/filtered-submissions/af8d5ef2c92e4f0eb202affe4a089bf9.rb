class Hamming

  def compute(strand1, strand2)
    if strand1.size < strand2.size
      shorter_strand, longer_strand = strand1.split(""), strand2.split("")
    else
      longer_strand, shorter_strand = strand1.split(""), strand2.split("")
    end

    hamming_distance = 0

    shorter_strand.each_index do |i|
      hamming_distance += 1 unless shorter_strand[i] == longer_strand[i]
    end

    hamming_distance

  end 
end
