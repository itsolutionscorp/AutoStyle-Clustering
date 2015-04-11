class Complement
  PAIRS = {'U' => 'A', 'T' => 'A', 'C' => 'G', 'G' => 'C'}

  def self.of_dna strand
    s = strand.upcase.chars
    raise ArgumentError if s.any? { |base| base == 'U' }
    translate s, 'U'
  end

  def self.of_rna strand
    s = strand.upcase.chars
    raise ArgumentError if s.any? { |base| base == 'T' }
    translate s, 'T'
  end

  private 

  def self.translate(strand, pyrimidine)
    translation = {'A' => pyrimidine}.merge PAIRS
    strand.map { |base| translation[base] }.join
  end
end
