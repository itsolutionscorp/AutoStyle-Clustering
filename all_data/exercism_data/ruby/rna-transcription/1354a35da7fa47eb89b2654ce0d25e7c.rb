class Complement
  RNA_BASES = 'CGTA'
  DNA_BASES = 'GCAU'

  def self.of_dna(rna)
    fail ArgumentError if transcribe(rna, RNA_BASES, DNA_BASES) == rna
    transcribe(rna, RNA_BASES, DNA_BASES)
  end

  def self.of_rna(dna)
    fail ArgumentError if transcribe(dna, DNA_BASES, RNA_BASES) == dna
    transcribe(dna, DNA_BASES, RNA_BASES)
  end

  def self.transcribe(genome_type, bases, compelemnts)
    genome_type.tr(bases, compelemnts)
  end
end
