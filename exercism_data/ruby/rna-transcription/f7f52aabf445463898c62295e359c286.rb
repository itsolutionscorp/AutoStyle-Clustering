class Complement
  def self.of_dna(dna)
    trans = {"C" => "G", "G" => "C", "T" => "A", "A" => "U"}
    result = ''
    dna.split('').each {|x| result << trans[x]}
    return result
  end
  def self.of_rna(rna)
    trans = {"C" => "G", "G" => "C", "U" => "A", "A" => "T"}
    result = ''
    rna.split('').each {|x| result << trans[x]}
    return result
  end
end
