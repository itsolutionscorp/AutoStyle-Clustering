class DNA
  def initialize(dna_string)
    @dna_array = dna_string.split("")
    @gatc = ["G","A","T","C"]
    raise ArgumentError unless validate?
  end

  def validate?
    @dna_array.all? {|letter| @gatc.include? letter}
  end

  def nucleotide_counts
    nuc_hash = {}
    nuc_hash["G"] = count(@gatc[0])
    nuc_hash["A"] = count(@gatc[1])
    nuc_hash["T"] = count(@gatc[2])
    nuc_hash["C"] = count(@gatc[3])
    nuc_hash
  end

  def count(letter)
    raise ArgumentError unless letter =~ /[GATCU]/
    @dna_array.count letter
  end
end
