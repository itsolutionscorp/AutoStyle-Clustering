class Complement

    DNA = ['G','C','T','A']
    RNA = ['C','G','A','U']

  def self.of_dna(strand)

    strand = strand.split(//)
    complement = Array.new

    strand.each do |s|
      i = DNA.index(s)
      c = RNA[i]
      complement.push(c)
    end

    rna_counterpart = complement.join
    rna_counterpart
  end

  def self.of_rna(strand)

    strand = strand.split(//)
    complement = Array.new

    strand.each do |s|
      i = RNA.index(s)
      c = DNA[i]
      complement.push(c)
    end

    dna_counterpart = complement.join
    dna_counterpart
  end

end
