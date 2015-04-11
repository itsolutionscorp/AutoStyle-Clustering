class Complement

  DNA_TO_RNA = {  "G" => "C", 
                 "C" => "G", 
                 "T" => "A",
                 "A" => "U" 
               }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    complement(strand)
  end

  def self.of_rna(strand)
    complement(strand,transformer = RNA_TO_DNA)
  end

  def self.complement(strand,transformer = DNA_TO_RNA)
    raise ArgumentError if strand.include?(bad_letter(substitute_hash))
    strand.gsub(/./,substitute_hash)
  end

  def self.bad_letter(transformer)
    (substitute_hash.values - substitute_hash.keys).join
  end

end
