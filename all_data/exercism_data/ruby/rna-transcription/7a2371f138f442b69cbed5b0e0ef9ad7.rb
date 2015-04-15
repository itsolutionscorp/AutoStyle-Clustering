class Complement

  DNA_TO_RNA =  { "G" => "C",
                  "C" => "G",
                  "T" => "A",
                  "A" => "U" }

  def self.of_dna dna
    transcribe dna, DNA_TO_RNA
  end

  def self.of_rna rna
    transcribe rna, DNA_TO_RNA.invert
  end

  private

    def self.transcribe strand, complements
      strand.each_char.with_object("") do |nuc,str|
        str << complements[nuc]
      end
    end

end
