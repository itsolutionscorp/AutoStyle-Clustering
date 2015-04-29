class DNA
  def initialize(dna_string)
    @dna_array = dna_string.split("")
    raise ArgumentError unless validate?
  end

  def validate?
    if @dna_array.include? "U"
      false
    elsif @dna_array.empty?
      true
    elsif @dna_array.join =~ /[G|A|T|C]/ 
      true
    end
  end

  def nucleotide_counts
    nuc_hash = {}
    nuc_hash["A"] = count('A')
    nuc_hash["T"] = count('T')
    nuc_hash["C"] = count('C')
    nuc_hash["G"] = count('G')
    nuc_hash
  end

  def count(letter)
    raise ArgumentError unless letter =~ /[G|A|T|C|U]/
    count = 0
    for x in @dna_array do
      count += 1 if x == letter
    end
    count
  end
end
