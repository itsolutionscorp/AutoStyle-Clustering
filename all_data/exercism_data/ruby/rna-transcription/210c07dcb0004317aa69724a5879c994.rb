class Hash
  def to_proc
    method(:[]).to_proc
  end
end

class Complement
  DNA_COMPLEMENT_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_COMPLEMENT_MAP = DNA_COMPLEMENT_MAP.invert

  def self.of_dna(dna)
    dna.each_char.map(&DNA_COMPLEMENT_MAP).join
  end

  def self.of_rna(rna)
    rna.each_char.map(&RNA_COMPLEMENT_MAP).join
  end
end
