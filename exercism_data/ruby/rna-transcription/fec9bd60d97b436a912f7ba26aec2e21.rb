class Complement

  def self.of_dna(nucleotides)

    @nucleotides = nucleotides
    self.complement("DNA")

  end

  def self.of_rna(nucleotides)

    @nucleotides = nucleotides
    self.complement("RNA")

  end

  def self.complement(strand)

    if strand == "RNA"
      self.match('U', 'A', 'A', 'T')
    else strand == "DNA"
      self.match('T', 'A', 'A', 'U' )
    end

  end

  def self.match(w, x, y, z)

    @nucleotides = @nucleotides.split(//)

    comp = []

    @nucleotides.each do |nucleo|
      if nucleo == 'C'
        nucleo = nucleo.replace 'G'
        comp << nucleo
      elsif nucleo == 'G'
        nucleo = nucleo.replace 'C'
        comp << nucleo
      elsif nucleo == w
        nucleo = nucleo.replace x
        comp << nucleo
      elsif nucleo == y
        nucleo = nucleo.replace z
        comp << nucleo
      end
    end

    comp.join

  end

end
