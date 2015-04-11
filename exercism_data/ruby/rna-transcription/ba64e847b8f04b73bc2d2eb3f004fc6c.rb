module Complement
  @dna_rna = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def of_dna(strand)
    String.new.tap do |str|
      strand.chars.map { |el| str << @dna_rna[el] }
    end
  end
end
