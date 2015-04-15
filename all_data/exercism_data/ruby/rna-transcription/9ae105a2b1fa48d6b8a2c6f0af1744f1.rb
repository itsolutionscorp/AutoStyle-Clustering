module Complement

  LOOKUP = {
    dna:  "ATCG",
    rna:  "UAGC"
  }

  def self.of_dna dna
    complement dna, :dna, :rna
  end

  def self.of_rna rna
    complement rna, :rna, :dna
  end

  # Let's avoid code duplication
  private
  def self.complement data, from, to
    data.tr LOOKUP[from], LOOKUP[to]
  end

end
