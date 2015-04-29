class DNA < String
  def count(nucleotide)
    raise ArgumentError unless %w{A T C G U}.include? nucleotide
    super
  end

  def nucleotide_counts
    %w{A T C G}.each_with_object({}){|nucleotide, counts| counts[nucleotide] = count(nucleotide)}
  end
end
