 # Don't hard-code "U" in the program.
 # You know which ones are recognized, because you have this: ```ruby values = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0} ```
 # If it's not in that list, it's unrecognized.
 #             kytrinyx

 # Specifically raise an argument error if the nucleotide in question is unrecognized.
 # Otherwise, just count it.
 #             kytrinyx

 ### Katrina, i can't reconcile these two comments.
 ### If i should raise an error for all unrecognized nucleotides, why does "U" equal 0 in the test?

class DNA

  attr_reader :strand

  def initialize(dna)
    @strand = dna.split('')
  end

  def count(nucleotide)
    if recognized_nucleotide.include?(nucleotide)
      strand.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    values = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    strand.each do |nucleotide|
      values[nucleotide] = count(nucleotide)
    end
    values
  end

  def recognized_nucleotide
    %w(A T C G U)
  end

end
