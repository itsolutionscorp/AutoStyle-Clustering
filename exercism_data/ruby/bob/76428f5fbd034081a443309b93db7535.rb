class DNA
  def initialize(dna_string)
    @string = dna_string
  end

  def to_rna
    string.tr 'T', 'U'
  end

  protected

  attr_reader :string
end
