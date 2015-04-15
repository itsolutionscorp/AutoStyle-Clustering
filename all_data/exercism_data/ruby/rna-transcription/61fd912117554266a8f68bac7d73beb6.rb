module Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' =>'A', 'A' => 'U' }
  RNA_TO_DNA = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }

  def self.of_dna(str)
    str.length.times { |i| str[i] = DNA_TO_RNA[str[i]] }
    str
  end

  def self.of_rna(str)
    str.length.times { |i| str[i] = RNA_TO_DNA[str[i]] }
    str
  end
end
