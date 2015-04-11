class Nucleotide
  DNA = %W(A C G T)
  RNA = %W(A C G U)

  def initialize(nucleotide, types = DNA+RNA)
    raise ArgumentError.new("#{nucleotide} is not a valid value") unless types.include?(nucleotide)
    @nucleotide = nucleotide
  end

  def to_s
    @nucleotide
  end

  def ==(obj)
    @nucleotide == obj.to_s
  end

  def self.dna(nucleotide)
    new(nucleotide, DNA)
  end
end

class DNA

  def initialize(sequence)
    @nucleotides = sequence.chars.map{|c| Nucleotide.dna(c)}
  end

  def count(nucleotide)
    @nucleotides.count(Nucleotide.new(nucleotide))
  end

  def nucleotide_counts
    Nucleotide::DNA.each_with_object(Hash.new(0)) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

end
