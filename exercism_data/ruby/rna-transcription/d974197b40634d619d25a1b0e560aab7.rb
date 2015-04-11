module Complement
  # An "abstract" translation map which associates single characters.
  DNA_TO_RNA_MAP = [%w(G C), %w(C G), %w(T A), %w(A U)]

  # Actual translation maps, implemented as hashes.
  DNA_TO_RNA = Hash[DNA_TO_RNA_MAP]
  RNA_TO_DNA = Hash[DNA_TO_RNA_MAP.map(&:reverse)]

  def self.of_dna(dna)
    translate_through_map(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    translate_through_map(rna, RNA_TO_DNA)
  end

  class << self
    private

    # Replace each character in `str` with its corresponding character according
    # to `map`.
    def translate_through_map(str, map)
      str.chars.map { |ch| map[ch] }.join('')
    end
  end
end
