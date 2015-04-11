class DNA
  attr_reader :nucleotides

  DNA_NUCLEOTIDES = [ 'A', 'C', 'G', 'T' ]
 
  def initialize(nucleotides)
    @nucleotides = nucleotides.split('').sort
    validate_as_dna(@nucleotides)
    @dna_nucleotide_count_result_default = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0} 
  end

  def count(nucleotide)
    Nucleotide.count(nucleotide, nucleotides)
  end

  def nucleotide_counts
    nucleotides.each_with_object(@dna_nucleotide_count_result_default) do |nucleotide, count_results|
      unless count_results.has_key? [nucleotide] 
        count_results[nucleotide] = count(nucleotide)
      end
    end
  end

  def validate_as_dna(candidates)
    candidates.uniq.each do |possible_nucleotide|
      unless DNA_NUCLEOTIDES.include? possible_nucleotide
        raise ArgumentError
      end
    end
  end

end

class Nucleotide

  NUCLEOTIDES = [ 'A', 'C', 'G', 'T', 'U' ] 

  def self.count(nucleotide, nucleotides)
    if validate_as_nucleotide(nucleotide)
      nucleotides.select {|n| n.eql? nucleotide}.count
    else
      raise ArgumentError
    end
  end

  def self.validate_as_nucleotide(candidate)
    NUCLEOTIDES.include? candidate 
  end

end
