class DNA
  def initialize(dna_string)
    @dna_array = dna_string.split("")
    @gatc = "GATC".chars.to_a
    raise ArgumentError unless valid?
  end

  def nucleotide_counts
    nuc_hash ||= {}
    @gatc.each { |letter| nuc_hash[letter] = count(letter) }
    nuc_hash
  end

  def count(letter)
    raise ArgumentError unless letter =~ /[GATCU]/
    @dna_array.count letter
  end

  private

  def valid?
    @dna_array.each do |letter|
      return false unless @gatc.include? letter
    end
  end
end
