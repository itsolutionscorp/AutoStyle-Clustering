class Complement

  DNA_TO_RNA = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(str)
    rna = ""
    str.chars.each do |c|
      begin
        rna += DNA_TO_RNA[c]
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
        dna += RNA_TO_DNA[c]
      rescue 
        raise ArgumentError
      end

    end
    return dna
  end

end
