# RNA transcription exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

class Complement

  COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'U',
    'T' => 'A'
  }

  def self.of_dna(dna)
    dna.split('').inject(''){|r, b| r += COMPLEMENTS[b] }
  end

  def self.of_rna(rna)
    rna.split('').inject(''){|d, b| d += COMPLEMENTS.invert[b] }
  end

end
