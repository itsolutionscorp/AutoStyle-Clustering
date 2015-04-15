class Complement

  TRANSFORM = {  "G" => "C", 
                 "C" => "G", 
                 "T" => "A",
                 "A" => "U" 
              }

  def self.of_dna(strand)
    transform(strand,TRANSFORM,/U/)
  end

  def self.of_rna(strand)
    transform(strand,TRANSFORM.invert,/T/)
  end

  def self.transform(strand,substitute_hash,bad_letter)
    raise ArgumentError if strand =~ bad_letter
    strand.gsub(/./,substitute_hash)
  end

end
