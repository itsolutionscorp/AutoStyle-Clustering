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

  def self.of_rna(dna)
    complement(dna,transformer = RNA_TO_DNA)
  end

  def self.complement(strand,transformer = DNA_TO_RNA)
    raise ArgumentError if strand.include?(bad_letter(transformer))
    strand.gsub(/./,substitute_hash)
  end

  def self.bad_letter(transformer)
    (substitute_hash.values - substitute_hash.keys).join
  end

end
