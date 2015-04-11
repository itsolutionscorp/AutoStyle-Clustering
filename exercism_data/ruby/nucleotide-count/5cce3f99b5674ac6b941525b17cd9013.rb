class DNA < String

  def nucleotides
    @nucleotides = { 'C' => 0, 'G' => 0, 'A' => 0, 'T' => 0, 'U' => 0 }  
  end

  def count(nucleotide)
    raise ArgumentError, 'Argument is not vald nucleotide' unless nucleotides.has_key?(nucleotide)
    scan(nucleotide).count
  end

  def nucleotide_counts
    split('').each_with_object(nucleotides) { |nucleotide, nucleotide_count| nucleotide_count[nucleotide] += 1 }.delete_if {|key| key == 'U'}
  end

end
