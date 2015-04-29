class Complement
  @complement_map={G:"C",C:"G",T:"A",A:"U"};
  def self.of_dna(dna)
    rna="";
    dna.each_char do |nucleotide|
        nucleotide_sym=nucleotide.to_sym;
        rna_nucleotide=@complement_map[nucleotide_sym];
        if(rna_nucleotide.nil?)
          raise ArgumentError;
        else
          rna+=rna_nucleotide;
        end 
    end
    return rna;
  end
  def self.of_rna(rna)
    dna="";
    rna.each_char do |nucleotide|
       dna_nucleotide=@complement_map.select{|k,v|v==nucleotide}
       if(dna_nucleotide.nil?||dna_nucleotide.length==0)
          raise ArgumentError;
        else
          dna+=dna_nucleotide.keys[0].to_s;
       end
    end
    return dna;
  end
end
