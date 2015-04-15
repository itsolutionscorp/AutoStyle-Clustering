#
# Complement Test Class
#

class Complement
  DNA_STRING = "GCTA"
  RNA_STRING = "CGAU"
  def self.of_dna(r)
    r.tr(DNA_STRING,RNA_STRING)
  end

  def self.of_rna(d)
    d.tr(RNA_STRING,DNA_STRING)
  end
end
