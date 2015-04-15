# Class documentation comment?
class Complement
  DNA = { 'C' => 'G',
          'G' => 'C',
          'T' => 'A',
          'A' => 'U' }

  RNA = { 'C' => 'G',
          'G' => 'C',
          'T' => 'A',
          'A' => 'T',
          'U' => 'A' }

  def initialize
  end

  def self.of_dna(strand)
    strand.split('').map { |d| DNA[d] }.join('')
  end

  def self.of_rna(strand)
    strand.split('').map { |r| RNA[r] }.join('')
  end
end
