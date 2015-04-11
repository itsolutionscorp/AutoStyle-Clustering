class Complement
  def self.of_dna(nucleotides)
    code = nucleotides.split('')
    strand = code.map do |nucleotide|
    case nucleotide
    when 'C' then 'G'
    when 'G' then 'C'
    when 'T' then 'A'
    when 'A' then 'U'
    end
  end
  strand.join
  end

  def self.of_rna(nucleotides)
    code = nucleotides.split('')
    strand = code.map do |nucleotide|
    case nucleotide
    when 'C' then 'G'
    when 'G' then 'C'
    when 'A' then 'T'
    when 'U' then 'A'
    end
  end
    strand.join
  end
end
