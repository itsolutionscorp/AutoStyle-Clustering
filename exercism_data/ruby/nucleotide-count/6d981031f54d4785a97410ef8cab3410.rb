class DNA
  def initialize(sequence)
    @sequence = sequence
  end
  
  def count(target_symbol)
    return 0 if target_symbol.empty?
    raise ArgumentError if !nucleotide?(target_symbol)
    @sequence.chars.select { |symbol| symbol == target_symbol }.count
  end
  
  def nucleotide_counts
    @sequence.chars.each_with_object(empty_nucleotide_hash) do |symbol, count|
      count[symbol] += 1
    end
  end
  
  private
  
  def nucleotide?(symbol)
    "ACGTU".include?(symbol)
  end
  
  def empty_nucleotide_hash
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end
end
