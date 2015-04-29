## Finds the number of differences (Hamming difference)between two
#pieces of DNA, dna_a and dna_b
#I used a functional approach for this.  First, I find the smaller of
#the two sequences, and take it's length.  Then I select all of the
#pieces that aren't similar between them, and count the resulting array.
#
#I tried doing it a slightly different way, where I used the minimum dna
#strand as the basis for a functional approach.  This seemed to be much
#more complicated.  It's kept below, commented out.

class Hamming
  def self.compute(dna_a,dna_b)
    min_length = [dna_a.length, dna_b.length].min 
    differences = (0..min_length-1).select{|index| dna_a[index] != dna_b[index]}.count
  end

  #Alternative version
  #def self.compute(dna_a,dna_b)
  #  as_arrays = [dna_a, dna_b].sort_by{|dna| dna.length}.map{|dna| dna.split("")}
  #  comparisons = as_arrays[0].zip dnas_as_arrays[1]
  #  differences = comparisons.select{|a,b| a!= b}.count
  #end
  #
end
