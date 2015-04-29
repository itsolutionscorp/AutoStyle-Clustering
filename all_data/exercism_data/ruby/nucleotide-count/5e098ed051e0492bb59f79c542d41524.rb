class DNA
  def initialize(strain)
    raise ArgumentError unless strain =~ /\A[ATCG]*\Z/

    @strain = strain.split(//)
  end

  def count(nucleotide)
    if ['A', 'T', 'C', 'G'].member? nucleotide
      nucleotide_counts[nucleotide]
    elsif nucleotide == 'U'
      0
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    @nucleotide_counts ||=
      @strain.each_with_object({'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}) do |nuc, counts|
        counts[nuc] += 1
      end
  end
end
