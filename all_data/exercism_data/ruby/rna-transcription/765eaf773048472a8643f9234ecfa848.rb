class Complement
  def self.of_dna(nucleotides)
    finish(strand = split_code(nucleotides).map {|nucleotide| dna(nucleotide)})
  end

  def self.of_rna(nucleotides)
    finish(strand = split_code(nucleotides).map {|nucleotide| rna(nucleotide)})
  end

  def self.split_code(nucleotides)
    nucleotides.chars
  end

  def self.finish(strand)
    strand.join
  end

  def self.dna(nucleotide)
    case nucleotide
    when 'C' then 'G'
    when 'G' then 'C'
    when 'T' then 'A'
    when 'A' then 'U'
    end
  end

  def self.rna(nucleotide)
    case nucleotide
    when 'C' then 'G'
    when 'G' then 'C'
    when 'A' then 'T'
    when 'U' then 'A'
    end
  end
end
