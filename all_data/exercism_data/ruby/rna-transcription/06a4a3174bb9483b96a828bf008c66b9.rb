class NucleicAcid
  def initialize(strand)
    @strand = strand
  end

  def ==(object)
    @strand.to_s == object.to_s
  end

  def to_s
    @strand
  end

  def to_str
    @strand
  end
end

class RibonucleicAcid < NucleicAcid
end


class DeoxyribonucleicAcid < NucleicAcid
  def to_rna
    RibonucleicAcid.new(@strand.tr 'T', 'U')
  end
end
