class DNA

  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(match_strand)
    @match_strand = match_strand.chars
    set_length
    for i in (0..@length-1)
      is_match?(i)
    end
    @hamms
  end

  private

  def strand_length
    @length_orginal = @strand.length
  end

  def match_strand_length
    @length_other = @match_strand.length
  end

  def set_length
    strand_length
    match_strand_length
    @length = 0
    if @length_orginal < @length_other
      @length = @length_orginal
    else
      @length = @length_other
    end
  end

  def is_match?(i)
    @hamms = 0
    if @strand[i] != @match_strand[i]
      @hamms += 1
    end
  end

end
