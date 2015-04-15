class Complement

  DNA = 'dna'
  RNA = 'rna'

  def self.of_dna(rna_str)
    rna_arr = rna_str.split('')
    dna_str = ''
    rna_arr.map { |x| dna_str << self.complement(x, RNA) }
    dna_str
  end

  def self.of_rna(dna_str)
    dna_arr = dna_str.split('')
    rna_str = ''
    dna_arr.map { |x| rna_str << self.complement(x, DNA) }
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
        return 'U' if type == RNA
        return 'T' if type == DNA
      when 'U'
        return 'A'
      else 
        return ''
      end
    end
end
