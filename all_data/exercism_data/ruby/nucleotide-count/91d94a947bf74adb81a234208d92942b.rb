class DNA
  attr_reader :nucleotide_counts

  def initialize(nucleotides)
    @nucleotide_counts = get_nucleotide_hash(nucleotides)
  end

  def count(base)
    raise ArgumentError unless @nucleotide_counts.keys.push("U").include?(base)
    base == "U" ? 0 : @nucleotide_counts[base]
  end

  def get_nucleotide_hash(nucleotides)
    nucleotide_counts = {'A'=>0, 'T'=>0, 'C'=>0, 'G'=>0}
    nucleotides.split("").each do |base|
      raise ArgumentError unless nucleotide_counts.keys.include?(base)
      nucleotide_counts[base]+=1
    end
    nucleotide_counts
  end
end
