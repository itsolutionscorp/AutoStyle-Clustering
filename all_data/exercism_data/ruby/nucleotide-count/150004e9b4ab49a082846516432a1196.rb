class DNA < String

  def count(nucleotide)
    raise ArgumentError, 'Argument is not vald nucleotide' unless ['C','G','A','T','U'].include? nucleotide
    scan(nucleotide).count
  end

  def nucleotide_counts
    nucleotde_count = {'C' => 0, 'G' => 0, 'A' => 0, 'T' => 0}
    split('').each_with_object(nucleotde_count) { |nucleotide, nucleotde_count| nucleotde_count[nucleotide] += 1 }
  end

end
