class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    limit = @strand.size < other_strand.size ? @strand.size : other_strand.size

    splitted_original = split(@strand, limit)
    splitted_other = split(other_strand, limit)

    splitted_original.zip(splitted_other).inject(0) { |h, e| e[0]==e[1] ? h+0 : h+1 }
  end

  def split(strand, limit)
    strand.split(//)[0..(limit-1)]
  end

  def call(strand)
    @strand = strand

    self
  end
end
