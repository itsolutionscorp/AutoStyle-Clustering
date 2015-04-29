class Complement
  @mapping = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  class << self

    def of_dna(dna)
      dna.split(//).collect! { |e| e = @mapping[e] }.join('')
    end

    def of_rna(rna)
      rna.split(//).collect! { |e| e = @mapping.invert[e] }.join('')
    end
  end
end
