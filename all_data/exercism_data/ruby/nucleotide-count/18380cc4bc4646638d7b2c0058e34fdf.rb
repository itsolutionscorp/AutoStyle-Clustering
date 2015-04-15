class DNA
  VALID_DNA_NUCLEOTIDES = ["A", "C", "G", "T"]
  VALID_RNA_NUCLEOTIDES = ["A", "C", "G", "U"]

  attr_reader = :nucleotides

  def initialize dna_str
    @@valid_dna_nucleotides = VALID_DNA_NUCLEOTIDES
    @@valid_rna_nucleotides = VALID_RNA_NUCLEOTIDES
    @nucleotides = dna_str.to_s.chars
    validate_dna
    validate_rna
  end

  def count n
    validate_nucleotide n
    count = 0
    @nucleotides.each {|nucleotide| 
        count+=1 if nucleotide == n
    }
    count
  end

  def nucleotide_counts
    @nucleotides.each_with_object({"A"=> 0, "C"=>0, "G"=>0, "T"=>0}) {|nucleotide, histogram|
      histogram[nucleotide]+=1
    }
  end

  private
  def all_valid_nucleotides
    @@valid_dna_nucleotides | @@valid_rna_nucleotides
  end

  def nucleotide? n
    return all_valid_nucleotides.include?(n) ? true : false
  end

  def dna?
    (@nucleotides - @@valid_dna_nucleotides).empty?  
  end

  def rna?
    (@nucleotides - @@valid_rna_nucleotides).empty? and not dna?  
  end

  def validate_dna
    raise ArgumentError, "Invalid DNA string" if not dna?
  end

  def validate_rna
    raise ArgumentError, "String is an RNA string" if rna?
  end

  def validate_nucleotide n
    raise ArgumentError, "Invalid nucleotide" if not nucleotide? n
  end
end
