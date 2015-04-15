# Class documentation comment?
class Complement
  DNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }
  RNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'T',
    'U' => 'A'
  }

  def initialize
  end

  def self.of_dna(strand)
    dna_array = strand.split('')
    dna_array.map { |d| DNA[d] }.join('')
  end

  def self.of_rna(strand)
    rna_array = strand.split('')
    rna_array.map { |r| RNA[r] }.join('')
  end
end
