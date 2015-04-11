##RNA/DNA Complement (exercism.io)
##John Youngblood
##1/26/2015
class Complement
  def self.of_dna(dna)
    dna.gsub(/[g]/imx, 'X').gsub(/[c]/imx, 'G').gsub(/[a]/imx, 'U').gsub(/[t]/imx, 'A').gsub(/[x]/imx, 'C')
  end
  def self.of_rna(rna)
    rna.gsub(/[g]/imx, 'X').gsub(/[c]/imx, 'G').gsub(/[a]/imx, 'T').gsub(/[u]/imx, 'A').gsub(/[x]/imx, 'C')
  end
end
