class DNA < String
  def count(nucleotide)
    raise ArgumentError unless %w{A T C G U}.include? nucleotide
    super
  end

  def nucleotide_counts
    Hash[
      "A", count("A"),
      "T", count("T"),
      "C", count("C"),
      "G", count("G")
    ]
  end
end
