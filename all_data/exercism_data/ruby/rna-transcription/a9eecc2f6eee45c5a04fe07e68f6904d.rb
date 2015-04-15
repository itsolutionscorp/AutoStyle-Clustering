class Complement
  class << self
    TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    TO_DNA = TO_RNA.invert

    def of_dna(dna)
      dna.split('').map { |x| TO_RNA[x] }.join
    end

    def of_rna(rna)
      rna.split('').map { |x| TO_DNA[x] }.join
    end
  end
end
