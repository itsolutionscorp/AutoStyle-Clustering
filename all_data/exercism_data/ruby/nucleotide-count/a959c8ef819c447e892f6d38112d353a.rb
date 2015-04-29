DNA =  Struct.new(:nucleotides) do

  def count(input)
    unless nucleotide?(input)
      raise ArgumentError
    end
    nucleotides.count(input)
  end

  def nucleotide_counts
    { 'A' => count('A'),'T' => count('T'),'G' => count('G'),'C' => count('C')}
  end

  def nucleotide?(input)
    %w(A T C G U).include?(input)
  end

end
