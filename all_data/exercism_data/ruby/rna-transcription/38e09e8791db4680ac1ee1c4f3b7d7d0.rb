class Complement
  def self.of_dna(str)
    h = {G:'C', C:'G', T:'A', A:'U'}
    str.gsub(/[GCTA]/) {|c| c = h[c.to_sym] }
  end
  def self.of_rna(str)
    h = {C:'G', G:'C', U:'A', A:'T'}
    str.gsub(/[CGUA]/) {|c| c = h[c.to_sym] }
  end
end
