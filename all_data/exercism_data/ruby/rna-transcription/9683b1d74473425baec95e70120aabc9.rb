class Complement
  @nucls = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  class << self
    def rna_dna_repl
      @rna_nucls ||= @nucls
    end

    def dna_rna_repl
      @dna_nucls ||= @nucls.invert
    end

    def convert(acid, repl)
      acid.chars.collect do |c|
        repl[c]
      end.join('')
    end

    def of_dna(rna)
      convert(rna, rna_dna_repl)
    end

    def of_rna(dna)
      convert(dna, dna_rna_repl)
    end
  end
end
