URACIL_CODE = 'U'
THYMINE_CODE = 'T'

class RibonucleicAcid < String
end

class DeoxyribonucleicAcid < String
  def to_rna
    RibonucleicAcid.new rna_string
  end
  def rna_string
    gsub(THYMINE_CODE, URACIL_CODE)
  end
end
