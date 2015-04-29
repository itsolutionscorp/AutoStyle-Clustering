class Nucleotide < Array
  def self.from_dna(str)
    fail ArgumentError if str.match(/[^ACGT]/)
    new(str.chars)
  end

  def histogram
    bases = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
    each_with_object(bases) { |e, a| a[e] += 1 }
  end
end
