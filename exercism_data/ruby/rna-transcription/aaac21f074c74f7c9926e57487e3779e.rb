#
# Complement Test Class
#

class Complement
  def self.of_dna(r)
    dna2rna = { "G" => "C",
                "C" => "G",
                "T" => "A",
                "A" => "U"
              }
    n = ""
    r.chars.each { |x| n = n + dna2rna[x] }
    n
  end

  def self.of_rna(d)
    rna2dna = { "C" => "G",
                "G" => "C",
                "A" => "T",
                "U" => "A"
              }
    n = ""
    d.chars.each { |x| n = n + rna2dna[x] }
    n
  end
end
