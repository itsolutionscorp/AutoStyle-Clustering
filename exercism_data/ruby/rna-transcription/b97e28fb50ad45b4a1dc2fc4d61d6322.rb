class Complement

  DNA_RNA = {
    'G'=>'C',
    'C'=>'G',
    'T'=>'A',
    'A'=>'U'
  }

  def self.of_dna(dna)
    dna.chars.collect { |c|
      DNA_RNA[c]
    }.join
  end

  def self.of_rna(rna)
    rna.chars.collect { |c|
      DNA_RNA.key(c)
    }.join
  end

end
