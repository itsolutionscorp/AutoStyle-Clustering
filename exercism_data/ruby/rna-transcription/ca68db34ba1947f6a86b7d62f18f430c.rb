# Return DNA or RNA complement
class Complement
  INVALID_INPUT = 'Invalid input'
  def self.to_rna(char)
    case char
    when 'G' then 'C'
    when 'C' then 'G'
    when 'T' then 'A'
    when 'A' then 'U'
    else fail INVALID_INPUT
    end
  end

  def self.to_dna(char)
    case char
    when 'C' then 'G'
    when 'G' then 'C'
    when 'A' then 'T'
    when 'U' then 'A'
    else fail INVALID_INPUT
    end
  end

  def self.transcribe(seq, type)
    result = []
    case type
    when :to_dna then (0...seq.length).step { |i| result[i] = to_dna(seq[i]) }
    when :to_rna then (0...seq.length).step { |i| result[i] = to_rna(seq[i]) }
    else fail INVALID_INPUT
    end
    result.join
  end

  def self.of_dna(dna)
    transcribe(dna, :to_rna)
  end

  def self.of_rna(rna)
    transcribe(rna, :to_dna)
  end
end
