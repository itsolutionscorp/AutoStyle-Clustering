class Complement
  G='G'.ord; C='C'.ord; T='T'.ord; A='A'.ord; U='U'.ord
  DNA_TO_RNA = { G => C, C => G, T => A, A => U }
  RNA_TO_DNA = Hash[ DNA_TO_RNA.values.zip DNA_TO_RNA.keys ]

  def self.of_dna(s) s.unpack('C*').map { |c| DNA_TO_RNA[c] }.pack('C*') end
  def self.of_rna(s) s.unpack('C*').map { |c| RNA_TO_DNA[c] }.pack('C*') end
end
