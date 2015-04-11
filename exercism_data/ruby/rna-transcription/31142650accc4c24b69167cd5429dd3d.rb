class Complement
  DNA = {acctual: 'GCTA', complement: 'CGAU'}
  RNA = {acctual: 'CGAU', complement: 'GCTA'}

  def self.of_dna(s)
    s.tr(DNA[:acctual],DNA[:complement])
  end

  def self.of_rna(s)
    s.tr(RNA[:acctual],RNA[:complement])
  end
end
