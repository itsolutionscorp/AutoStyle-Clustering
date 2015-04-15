require 'pry'

class Complement
  DNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    strand.chars.map do |char|
      DNA_MAP[char]
    end.join
  end

  def self.of_rna(strand)
    strand.chars.map do |char|
      DNA_MAP.rassoc(char)[0]
    end.join
  end
end
