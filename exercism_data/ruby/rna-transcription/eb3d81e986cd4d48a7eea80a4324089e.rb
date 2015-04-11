class Strand
  attr_accessor :nucleotides

  def initialize(strand)
    @nucleotides = build_from(strand)
  end

  def transcribe
    nucleotides.map { |nt| get_complement_of(nt) }.join("")
  end

  def get_complement_of(nt)
    raise "Abstract method error"
  end

  private

  def build_from(strand)
    strand.split("")
  end
end

class DNA < Strand
  def get_complement_of(nt)
      case nt.to_s
      when 'C' then 'G'
      when 'G' then 'C'
      when 'T' then 'A'
      when 'A' then 'U'
      else
        raise "unknown nucleotide: `#{nt}`"
      end
  end
end

class RNA < Strand
  def get_complement_of(nt)
      case nt.to_s
      when 'C' then 'G'
      when 'G' then 'C'
      when 'U' then 'A'
      when 'A' then 'T'
      else
        raise "unknown nucleotide: `#{nt}`"
      end
  end
end

class Complement
  def self.of_dna(strand)
    DNA.new(strand).transcribe
  end

  def self.of_rna(strand)
    RNA.new(strand).transcribe
  end
end
