class Complement
  def self.of_dna(nuc)
    test = nuc.delete "ACGT"
    if test.empty?
      nuc.tr('CGTA', 'GCAU')
    else
      arg_error('dna')
    end
  end

  def self.of_rna(nuc)
    test = nuc.delete "ACGU"
    if test.empty?
      nuc.tr('CGUA', 'GCAT')
    else
      arg_error('rna')
    end
  end

  def self.arg_error(acid)
    raise ArgumentError, ("Argument is not right #{acid} nucleotide")
  end
end
