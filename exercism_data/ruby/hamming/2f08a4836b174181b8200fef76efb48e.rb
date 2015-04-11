class Hamming

  def Hamming.compute(strand1, strand2)
    x=0
    array=[]
    i =0
    while i < strand_length(strand1, strand2)
      if strand1[i] == strand2[i]
        x = 0
      elsif strand1[i] != strand2[i]
        x = 1
      end
      array << x
      i+=1
    end
    return array.inject(:+)
  end


  def Hamming.strand_length(strand1, strand2)
    min_strand_length = strand1.length <=> strand2.length
    if min_strand_length == 0
      num = strand1.length
    elsif min_strand_length == -1
      num = strand1.length
    elsif min_strand_length == 1
      num = strand2.length
    end
    return num
  end


end
