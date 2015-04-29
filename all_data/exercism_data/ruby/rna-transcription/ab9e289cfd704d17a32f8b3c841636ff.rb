class Complement
  def self.of_dna(nuc)
    examine_nucleotide("dna", nuc)
  end

  def self.of_rna(nuc)
    examine_nucleotide("rna", nuc)
  end

  def self.examine_nucleotide(na, nuc)
    if na == "dna"
      test = nuc.delete "ACGT"
    else
      test = nuc.delete "ACGU"
    end

    if test.empty?
      if na == "dna"
        nuc.tr('CGTA', 'GCAU')
      else
        nuc.tr('CGUA', 'GCAT')
      end
    else
      arg_error(na)
    end
  end

  def self.arg_error(acid)
    raise ArgumentError, ("Argument is not right #{acid} nucleotide")
  end
end
