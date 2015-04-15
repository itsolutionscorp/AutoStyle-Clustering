module Complement
  TRANSLATE = ->(strand, key) { strand.chars.map { |n| key[n]}.join }
  DNA_KEY = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    TRANSLATE.(strand, DNA_KEY)
  end

  def self.of_rna(strand)
    TRANSLATE.(strand, DNA_KEY.invert)
  end
end
