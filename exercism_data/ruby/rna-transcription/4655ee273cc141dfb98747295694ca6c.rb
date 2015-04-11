class Complement

  DNA_TO_RNA_MAPPING = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_TO_DNA_MAPPING = {"C" => "G", "G" => "C", "A" => "T", "U" => "A"}

  def self.of_dna(str)
    rna = ""
    str.chars.each do |c|
      begin
        rna += DNA_TO_RNA_MAPPING[c]
      rescue 
        raise ArgumentError
      end
    end
    return rna
  end

  def self.of_rna(str)
    dna = ""
    str.chars.each do |c|
      begin
        dna += RNA_TO_DNA_MAPPING[c]
      rescue 
        raise ArgumentError
      end

    end
    return dna
  end

end
