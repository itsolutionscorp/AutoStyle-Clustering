class DNA
  attr_reader :strand
  
  def initialize(strand)
    @strand = strand
  end

  def to_rna
    rna = RNA.new
    rnap = RNAP.new(self)
    rnap.transcript(rna)
    rna.strand 
  end
end

class RNA
  attr_reader :strand

  def initialize
    @strand = ""
  end

  def attach(nucleobase)
    @strand.concat(nucleobase) 
  end
end

class RNAP
  attr_reader :template

  def initialize(dna)
    @template = dna.strand
  end

  def transcript(rna)
    @template.chars.each do |nucleobase|
      rna.attach(apply_factors(nucleobase))
    end
  end

  def apply_factors(nucleobase)
    if nucleobase == 'T'
      'U'
    else
      nucleobase.dup
    end
  end
end
