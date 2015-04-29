class DNA
  NUCLEOTIDES = %w{A T C G}

  def self.valid_nucleotides
    NUCLEOTIDES
  end

  def initialize(string)
    raise ArgumentError unless valid_dna_string?(string)

    @string = string
    @count  = {}
  end

  def count(match)
    raise ArgumentError unless valid_nucleotide?(match, include_rna: true)

    @count[match] ||= @string.count(match)
  end

  def nucleotide_counts
    DNA.valid_nucleotides.each_with_object({}) { |ntide, counts| counts[ntide] = count(ntide) }
  end

  private

  def valid_dna_string?(string)
    string.chars.uniq.select { |ntide| !valid_nucleotide?(ntide) }.empty?
  end

  def valid_nucleotide?(ntide, options = {})
    if options[:include_rna]
      (DNA.valid_nucleotides + RNA.valid_nucleotides).include?(ntide)
    else
      DNA.valid_nucleotides.include?(ntide)
    end
  end
end

class RNA
  NUCLEOTIDES = %w{A C G U}

  def self.valid_nucleotides
    NUCLEOTIDES
  end
end
