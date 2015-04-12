module Hamming

  def compute( strand1, strand2)
    return 0 if strand1 == strand2

    sequence1 = strand1.split //
    sequence2 = strand2.split //
    sequence1, sequence2 = sequence2, sequence1 if sequence2.length < sequence1.length

    hamm = 0
    sequence1.each_with_index {|s,i| hamm +=1 if s!=sequence2[i]}
    hamm
  end
end
