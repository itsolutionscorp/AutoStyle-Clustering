class Complement

  def self.of_dna complement
    new(complement, :dna).detect!
  end

  def self.of_rna complement
    new(complement, :rna).detect!
  end

  def initialize complement, type = :dna
    @complement_chars = complement.split("")
    @type = type
  end

  def detect!
    @complement_chars.inject("") do |memo, c|
      memo += find_complement_for(c)
    end
  end

  private

  def find_complement_for(char)
    if @type == :dna
      detect_dna_complement(char)
    else
      detect_rna_complement(char)
    end
  end

  def detect_dna_complement(char)
    dna_complements.detect {|pair| pair.first == char }.last
  end

  def detect_rna_complement(char)
    rna_complements.detect {|pair| pair.first == char }.last
  end

  def rna_complements
    [
      ['C', 'G'],
      ['G', 'C'],
      ['U', 'A'],
      ['A', 'T']
    ]
  end

  def dna_complements
    [
      ['C', 'G'],
      ['G', 'C'],
      ['T', 'A'],
      ['A', 'U']
    ]
  end

end
