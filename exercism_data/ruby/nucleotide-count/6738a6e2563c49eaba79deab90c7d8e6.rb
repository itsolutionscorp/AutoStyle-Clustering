class DNA
  attr_reader :nucleotides

  DNA_NUCLEOTIDES = [ 'A', 'C', 'G', 'T' ]
 
  def initialize(nucleotides)
    @nucleotides = normalized_input(nucleotides)
    validate_as_dna(@nucleotides)
    @dna_nucleotide_count_result_default = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0} 
  end

  def count(nucleotide)
    Nucleotide.count_single(nucleotide, nucleotides)
  end

  def nucleotide_counts
    nucleotides.each_with_object(@dna_nucleotide_count_result_default) do |nucleotide, count_results|
      count_results[nucleotide] = count(nucleotide) unless count_results.key? [nucleotide] 
    end
  end

private

  def normalized_input(nucleotides)
    nucleotides.split('').sort
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

  def self.count_single(nucleotide, nucleotides)
    if validate_as_nucleotide(nucleotide)
      nucleotides.select {|n| n.eql? nucleotide}.count
    else
      raise ArgumentError
    end
  end

private

  def self.validate_as_nucleotide(candidate)
    NUCLEOTIDES.include? candidate 
  end

end
