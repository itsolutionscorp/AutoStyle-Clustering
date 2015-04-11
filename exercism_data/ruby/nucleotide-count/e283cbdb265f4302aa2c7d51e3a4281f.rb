class Nucleotide < Array
  def self.from_dna str
    str.match(/[^ACGT]/) ? fail(ArgumentError) : new(str.chars)
  end

  def histogram
    each_with_object('A' => 0, 'T' => 0, 'C' => 0, 'G' => 0) { |e, a| a[e] += 1 }
  end
end
