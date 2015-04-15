class Nucleotide

  HISTOGRAM = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}

  def self.from_dna str
    raise ArgumentError unless (str.chars - HISTOGRAM.keys).empty?
    @seq = str
    self
  end

  def self.count nuc
    @seq.count nuc
  end

  def self.histogram
    HISTOGRAM.each_key { |key| HISTOGRAM[key] = @seq.count(key) }
  end

end
