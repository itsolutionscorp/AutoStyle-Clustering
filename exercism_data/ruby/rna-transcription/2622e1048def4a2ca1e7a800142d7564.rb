class Complement
  BASE_MAP = { 'C' => 'G', 'G' => 'C' }
  DNA_MAP  = BASE_MAP.merge('T' => 'A', 'A' => 'U')
  RNA_MAP  = BASE_MAP.merge('U' => 'A', 'A' => 'T')

  def self.of_dna(proteins)
    convert(proteins, DNA_MAP)
  end

  def self.of_rna(proteins)
    convert(proteins, RNA_MAP)
  end

  def self.convert(proteins, mapping = {})
    proteins.to_s.chars.map { |protein| mapping.fetch(protein) }.join
  rescue KeyError
    raise ArgumentError
  end
end
