class Hamming
  def self.compute(strand1, strand2)
    return 0 if strand1 == strand2
    strand_arr1 = strand1.split("")
    strand_arr2 = strand2.split("")
    differences = 0
    strand_arr1.each_index do |index|
      nucleotide1 = strand_arr1[index]
      nucleotide2 = strand_arr2[index]
      return differences unless nucleotide1 && nucleotide2
      differences += 1 if nucleotide2 != nucleotide1
    end
    differences
  end
end
