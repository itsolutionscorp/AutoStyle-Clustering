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
    @strands.select { |symbol_pair| different?(symbol_pair) }.count
  end
  
  private
  
  def different?(symbol_pair)
    original_symbol = symbol_pair[0]
    target_symbol   = symbol_pair[1]
    (original_symbol != target_symbol) && !target_symbol.nil?
  end
end
