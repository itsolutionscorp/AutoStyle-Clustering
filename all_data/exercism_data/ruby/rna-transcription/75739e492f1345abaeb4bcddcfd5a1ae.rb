class Complement

  DNA_TO_RNA = {  "G" => "C", 
                 "C" => "G", 
                 "T" => "A",
                 "A" => "U" 
               }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    complement(dna)
  end

  def self.of_rna(rna)
    complement(rna,transformer = RNA_TO_DNA)
  end

  def self.complement(strand,transformer = DNA_TO_RNA)
    raise ArgumentError if strand.include?(bad_letter(transformer))
    strand.gsub(/./,transformer)
  end

  def self.bad_letter(transformer)
    (transformer.values - transformer.keys).join
  end

end
