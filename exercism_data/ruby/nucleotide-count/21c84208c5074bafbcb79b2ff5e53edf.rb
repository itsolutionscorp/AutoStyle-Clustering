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

  def self.dna(nucleotide)
    new(nucleotide, DNA)
  end
end

class DNA

  def initialize(sequence)
    @nucleotides = sequence.chars.map{|c| Nucleotide.dna(c)}
  end

  def count(nucleotide)
    nucleotide_counts[Nucleotide.new(nucleotide).to_s] || 0
  end

  def nucleotide_counts
    @counts ||= @nucleotides.each_with_object({'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}) do |n, counts|
      counts[n.to_s] += 1
    end
  end

end
