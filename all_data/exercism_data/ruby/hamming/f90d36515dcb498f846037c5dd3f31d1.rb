class DNA
  def initialize(strand)
    @strand = strand
  end
  
  def hamming_distance(another_strand)
    StrandsComparer.new(@strand, another_strand).count_differences
  end
end

class StrandsComparer
  def initialize(original, target)
    @strands = original.chars.zip(target.chars)
  end
  
  def count_differences
    @strands.select { |pair| different?(pair) }.count
  end
  
  private
  
  def different?(pair)
    (pair[0] != pair[1]) && !pair[1].nil?
  end
end
