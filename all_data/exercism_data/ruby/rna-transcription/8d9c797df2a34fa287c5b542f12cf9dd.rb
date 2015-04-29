class Complement
  @nucls = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  @nucls_inv = @nucls.invert

  class << self
    def of_dna(rna)
      rna.gsub(/[GCTA]/, @nucls)
    end

    def of_rna(dna)
      dna.gsub(/[CGAU]/, @nucls_inv)
    end
  end
end
