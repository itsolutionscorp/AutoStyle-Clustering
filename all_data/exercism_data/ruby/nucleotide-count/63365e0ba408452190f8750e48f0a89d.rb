class DNA < Struct.new(:sequence)
  def count(nucleotide)
    raise ArgumentError unless %w(A T C G U).include?(nucleotide)
    sequence.chars.count{|c| c == nucleotide }
  end

  def nucleotide_counts
    %w(A T C G).each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end
end
