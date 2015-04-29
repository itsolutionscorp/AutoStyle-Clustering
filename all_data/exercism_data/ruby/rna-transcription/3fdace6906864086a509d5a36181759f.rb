# Return DNA or RNA complement
class Complement
  def self.transcribe(seq, type)
    conv = %w(GCTA CGAU)
    case type
    when :to_rna then seq.tr conv[0], conv[1]
    when :to_dna then seq.tr conv[1], conv[0]
    else fail 'Invalid input'
    end
  end

  def self.of_dna(dna)
    transcribe(dna, :to_rna)
  end

  def self.of_rna(rna)
    transcribe(rna, :to_dna)
  end
end
