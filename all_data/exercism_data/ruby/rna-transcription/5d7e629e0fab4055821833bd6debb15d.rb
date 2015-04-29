module Complement
  TRANSLATE = ->(strand, key) { strand.chars.map { |n| key[n]}.join }

  def self.of_dna(strand)
    key = {
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
    }
    TRANSLATE.(strand, key)
  end

  def self.of_rna(strand)
    key = {
      'C' => 'G',
      'G' => 'C',
      'U' => 'A',
      'A' => 'T'
    }
    TRANSLATE.(strand, key)
  end
end
