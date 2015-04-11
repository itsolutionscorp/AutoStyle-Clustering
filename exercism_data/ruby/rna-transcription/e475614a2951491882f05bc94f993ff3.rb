class Complement

  DNA = 'dna'
  RNA = 'rna'

  def self.of_dna(rna_str)
    dna_str = ''
    rna_str.chars.map { |x| dna_str << self.complement(x, RNA) }
    dna_str
  end

  def self.of_rna(dna_str)
    rna_str = ''
    dna_str.chars.map { |x| rna_str << self.complement(x, DNA) }
    rna_str
  end

  private

    def self.complement(strand, type)
      case strand
      when 'G'
        return 'C'
      when 'C'
        return 'G'
      when 'T'
        return 'A'
      when 'A'
        return type == RNA ? 'U' : 'T'
      when 'U'
        return 'A'
      else 
        return ''
      end
    end
end
