class Complement
  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  def self.of_dna (dna)
    calculate(dna)
  end

  def self.of_rna (rna)
    calculate(rna)
  end

  private

  def self.calculate (strand)
    # Checks to see if the caller method includes rna,
    # then orients the Hash accordingly.
    key = if caller_locations(1,1)[0].label.include? "rna"
      COMPLEMENTS.invert
    else
      COMPLEMENTS
    end

    strand.chars.map do |x|
      key[x]
    end.join
  end
end


# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
