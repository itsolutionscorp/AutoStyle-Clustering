class DNA

  def initialize(dna_string)
    raise ArgumentError if dna_string =~ /[^ACGT]/
    @dna_string = dna_string
  end

  def count(char)
    raise ArgumentError if char =~ /[^ACGTU]/
    @dna_string.scan(char).size
  end

  def nucleotide_counts
    @dna_string.chars.each_with_object(nucleotide_hash) do |char, hsh|
      hsh[char] += 1
    end
  end


  private

  def nucleotide_hash
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

end
