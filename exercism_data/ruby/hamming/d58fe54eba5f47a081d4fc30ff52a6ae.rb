## Finds the number of differences (Hamming difference)between two
#pieces of DNA, dna_a and dna_b
#I used a functional approach for this.  First, I find the smaller of
#the two sequences, and take it's length.  Then I select all of the
#pieces that aren't similar between them, and count the resulting array.

class Hamming
  def self.compute(dna_a,dna_b)
    #min_length = [dna_a.length, dna_b.length].min 
    #differences = (0..min_length-1).select{|index| dna_a[index] != dna_b[index]}.count
    dnas_in_order = [dna_a, dna_b].sort_by{|dna| dna.length}
    dnas_as_arrays = dnsas_in_order.map{|dna| dna.split("")}
    comparisons = dnas_as_arrays.first.zip dnas_as_arrays.second
    differences = comparisons.select{|a,b| a!= b}.count
  end
end
