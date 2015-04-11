class Complement
  @complements = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U' }

  def self.of_dna (dna)
    rna = ''

    dna.each_char do |c|
      if @complements[c.upcase] == nil
        raise ArgumentError.new
      end
      rna += @complements[c.upcase]
    end

    return rna
  end

  def self.of_rna (rna)
    dna = ''

    rna.each_char do |c|
      if @complements.key(c.upcase) == nil
        raise ArgumentError.new
      end
      dna += @complements.key(c.upcase)
    end

    return dna
  end

end
