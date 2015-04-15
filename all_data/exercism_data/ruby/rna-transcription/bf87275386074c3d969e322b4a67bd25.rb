class Complement

  DNA = { 'C' => 'G', 
          'G' => 'C', 
          'T' => 'A', 
          'A' => 'U' }

  RNA = DNA.invert

  def self.of_dna(rna)
    convert(rna, DNA)
  end

  def self.of_rna(dna)
    convert(dna, RNA)
  end

  private

    def self.convert(sequence, converter)
      sequence.chars.map { |base| converter[base] }.join
    end
end
