class Complement
  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  def self.of_dna (rna)
    each_nucleotide(rna) do |x|
      COMPLEMENTS[x]
    end
  end

  def self.of_rna (dna)
    each_nucleotide(dna) do |x|
      COMPLEMENTS.invert[x]
    end
  end

  private

  def self.each_nucleotide (strand)
    result = []
    strand.chars.each do |nucleotide|
      result.push(yield nucleotide)
    end
    result.join('')
  end
end


# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
