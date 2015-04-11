class DNA

  TIDES = %w(A C G T)

  def initialize strand
    raise ArgumentError if strand[/[^#{TIDES.join}]/]
    @strand = strand
  end

  def count tide
    raise ArgumentError unless TIDES.include? tide
    @strand.scan(tide).count
  end

  def nucleotide_counts
    TIDES.each_with_object({}) do |tide,hsh|
      hsh[tide] = count tide
    end
  end

end
