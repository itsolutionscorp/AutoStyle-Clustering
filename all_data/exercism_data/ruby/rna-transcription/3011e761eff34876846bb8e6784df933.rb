class Complement

  def self.of_dna(rna)
    dna = [] 
    length = [rna.length].min
    (0...length).each do |i|
      if rna[i] == 'G'
        dna << 'C'
      elsif rna[i] == 'C'
        dna << 'G'
      elsif rna[i] == 'T'
        dna << 'A'
      elsif rna[i] == 'A'
        dna << 'U'
      elsif rna[i] == 'U'
        raise ArgumentError.new()
      end
    end
    dna.join
  end

  def self.of_rna(dna)
    rna = [] 
    length = [dna.length].min
    (0...length).each do |i|
      if dna[i] == 'G'
        rna << 'C'
      elsif dna[i] == 'C'
        rna << 'G'
      elsif dna[i] == 'U'
        rna << 'A'
      elsif dna[i] == 'A'
        rna << 'T'
      elsif dna[i] == 'T'
        raise ArgumentError.new()
      end
    end
    rna.join
  end
end
