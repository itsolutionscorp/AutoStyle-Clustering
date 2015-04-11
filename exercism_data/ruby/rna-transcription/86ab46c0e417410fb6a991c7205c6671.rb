#!/usr/bin/env ruby -w

class Complement
  def Complement.of_dna(strand)
    DNAComplementor.new.transcribe strand
  end

  def Complement.of_rna(strand)
    RNAComplementor.new.transcribe strand
  end
end

#----------------------------------------------------------------------------
class DNAComplementor
  def transcribe(strand)
    strand.tr('GCTA', 'CGAU')
  end
end

#----------------------------------------------------------------------------
class RNAComplementor
  def transcribe(strand)
    strand.tr('GCUA', 'CGAT')
  end
end
