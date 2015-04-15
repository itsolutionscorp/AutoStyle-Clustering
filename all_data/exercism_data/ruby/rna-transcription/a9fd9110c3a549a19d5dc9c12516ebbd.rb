class Complement
  DNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA = DNA.invert

  def self.convert(gens, constant)
    gens.each_char.map{ |gen| constant[gen] }.join
  end

  def self.of_dna(rnas)
    convert(rnas, DNA)
  end

  def self.of_rna(dnas)
    convert(dnas, RNA)
  end
end
