class DNA
  def initialize(strain)
    raise ArgumentError unless strain =~ /^[ATCG]*$/
    @strain = strain
    @nucleotide_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def count(letter)
    if !@nucleotide_counts.has_key?(letter)
      raise ArgumentError
    end
    @strain.count(letter)
  end

  def split
    @strain.split('')
  end

  def nucleotide_counts
    split.each do |letter|
      @nucleotide_counts[letter] += 1
    end
    @nucleotide_counts
  end
end
