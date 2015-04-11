class Complement
  def self.of_dna(strand)

    dna = ['G','C','T','A']
    rna = ['C','G','A','U']

    strand = strand.split(//)
    complement = Array.new

    strand.each do |s|
      i = dna.index(s)
      c = rna[i]
      complement.push(c)
    end

    c = complement.join
    c
  end

  def self.of_rna(strand)

    dna = ['G','C','T','A']
    rna = ['C','G','A','U']

    strand = strand.split(//)
    complement = Array.new

    strand.each do |s|
      i = rna.index(s)
      c = dna[i]
      complement.push(c)
    end

    c = complement.join
    c
  end

end
