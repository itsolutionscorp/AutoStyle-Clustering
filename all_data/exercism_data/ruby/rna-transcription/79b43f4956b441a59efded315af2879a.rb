class Complement
  DnaComplements = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

  RnaComplements = DnaComplements.invert

  def self.of_dna(strand)
    _complement_strand(strand, DnaComplements)
  end

  def self.of_rna(strand)
    _complement_strand(strand, RnaComplements)
  end

  def self._complement_strand(strand, complements)
    strand.gsub(/./) do |char|
      complements[char] || ''
    end
  end
end
