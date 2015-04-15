module Complement
  @@DNA_MAP ||= { 'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A' }
  def self.of_dna(string)
    string.chars.map { |n| @@DNA_MAP[n] }.join('')
  end

  @@RNA_MAP ||= { 'A' => 'T', 'C' => 'G', 'G' => 'C', 'U' => 'A' }
  def self.of_rna(string)
    string.chars.map { |n| @@RNA_MAP[n] }.join('')
  end
end
