class Complement
  @nucleotides = {
    'A' => 'U',
    'T' => 'A',
    'C' => 'G',
    'G' => 'C'
  }

  def initialize
    attr_reader :nucleotides
  end

  def self.of_dna(dna)
    result = []
    dna.split('').each do |nuc|
      item = @nucleotides[nuc]
      result.push(item) if item
    end
    result.join
  end

  def self.of_rna(rna)
    result = []
    rna.split('').each do |nuc|
      item = @nucleotides.invert[nuc]
      result.push(item) if item
    end
    result.join
  end
end
