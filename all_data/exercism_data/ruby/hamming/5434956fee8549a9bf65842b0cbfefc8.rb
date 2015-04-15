class Hamming
  def self.compute(source, target)
    source, target = cut_to_smallest_strand(source,target)
    nucleotide_pairs = source.split('').zip(target.split(''))
    nucleotide_pairs.reduce(0){|sum, nucleotide_pair| sum +  distance_for_nucleotide_pair(nucleotide_pair)}
  end

  private
  def self.distance_for_nucleotide_pair(pair)
    pair[0] == pair[1] ? 0 : 1
  end

  def self.cut_to_smallest_strand(a,b)
    smallest_length = [a.length , b.length].min
    return a[0,smallest_length], b[0,smallest_length]
  end
end
