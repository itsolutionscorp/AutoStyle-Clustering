class Complement

  def self.of_dna(dna)
    keys = %w(G C T A)
    values = %w(C G A U)
    hsh = keys.zip(values).to_h

    dna.split("").inject("") do |result, element|
      result << hsh[element]
      result
    end
  end

  def self.of_rna(rna)
    keys = %w(G C A U)
    values = %w(C G T A)
    hsh = keys.zip(values).to_h

    rna.split("").inject("") do |result, element|
      result << hsh[element]
      result
    end
  end
end
