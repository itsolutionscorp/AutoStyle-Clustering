class Complement
  DNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA = {
    "C" => "G" ,
    "G" => "C" ,
    "A" => "T" ,
    "U" => "A"
  }

  def self.convert(gens, constant)
    gens.chars.map{ |gen| constant[gen] }.join
  end

  def self.of_dna(rnas)
    convert(rnas, DNA)
  end

  def self.of_rna(dnas)
    convert(dnas, RNA)
  end
end
