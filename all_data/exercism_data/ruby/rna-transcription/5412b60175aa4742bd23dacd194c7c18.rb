class Complement
  def self.of_dna(dna_strand)
    dna_strand.chars.map(&method(:of_dna_nucleotide)).join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map(&method(:of_rna_nucleotide)).join
  end

  private

  def self.of_dna_nucleotide(nucleotide)
    case nucleotide
    when 'G' then 'C'
    when 'C' then 'G'
    when 'T' then 'A'
    when 'A' then 'U'
    end
  end

  def self.of_rna_nucleotide(nucleotide)
    case nucleotide
    when 'C' then 'G'
    when 'G' then 'C'
    when 'A' then 'T'
    when 'U' then 'A'
    end
  end
end
