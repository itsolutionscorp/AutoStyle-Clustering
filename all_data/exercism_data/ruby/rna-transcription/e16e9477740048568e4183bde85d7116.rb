class DeoxyribonucleicAcid < String
  def to_rna
    RibonucleicAcid.new(self.gsub(/T/,'U'))
  end
end

class RibonucleicAcid < String
end
