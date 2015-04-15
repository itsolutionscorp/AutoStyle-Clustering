class DNA
  def initialize(dna_string)
    @dna_array = dna_string.split("")
    @gatc = "GATC".chars.to_a
    raise ArgumentError unless valid?
  end

  def nucleotide_counts
    @gatc.each_with_object({}) do |letter, hash|
      hash[letter] = count(letter)
    end
  end

  def count(letter)
    raise ArgumentError unless letter =~ /[GATCU]/
    @dna_array.count letter
  end

  private

  def valid?
    @dna_array.uniq.each do |letter|
      return false unless @gatc.include? letter
    end
  end
end
