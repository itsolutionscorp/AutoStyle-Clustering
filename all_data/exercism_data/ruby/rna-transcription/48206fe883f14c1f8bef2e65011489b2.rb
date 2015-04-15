class Complement

  COMPS = { dna: 'GCTA',
            rna: 'CGAU' }

  def self.of_dna dna
    dna.tr COMPS[:dna], COMPS[:rna]
  end

  def self.of_rna rna
    rna.tr COMPS[:rna], COMPS[:dna]
  end

end
