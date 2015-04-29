class DNA
  def initialize(dna)
    @dna = dna
    @freq = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    calculate_freq
  end

  def count(nucleotide)
    valid_nucleotide? nucleotide
    @freq.fetch(nucleotide) { 0 }
  end

  def nucleotide_counts
    @freq
  end

  private

  def calculate_freq
    @dna.chars.each do |letter|
      @freq[letter] += 1
    end
  end

  def nucleotide_list
    @freq.keys + ['U']
  end

  def valid_nucleotide?(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide" unless nucleotide_list.include? nucleotide
  end
end
