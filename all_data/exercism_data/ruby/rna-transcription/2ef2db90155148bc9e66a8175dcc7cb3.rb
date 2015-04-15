class Complement
  BASE_MAP = { 'C' => 'G', 'G' => 'C' }
  DNA_MAP  = BASE_MAP.merge('T' => 'A', 'A' => 'U')
  RNA_MAP  = BASE_MAP.merge('U' => 'A', 'A' => 'T')

  def self.of_dna(proteins)
    new(proteins).send(:convert, DNA_MAP)
  end

  def self.of_rna(proteins)
    new(proteins).send(:convert, RNA_MAP)
  end

  def initialize(proteins)
    @proteins = proteins
  end

  private

  attr_reader :proteins

  def convert(mapping = {})
    proteins.chars.map { |protein| mapping.fetch(protein) }.join
  rescue KeyError
    raise ArgumentError
  end
end
