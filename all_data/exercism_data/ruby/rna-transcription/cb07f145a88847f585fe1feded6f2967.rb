class Complement
  def self.of_dna(dna)
    map = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    convert(dna, map)
  end
  
  def self.of_rna(rna)
    map = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
    convert(rna, map)
  end
  
  def self.convert(str, map)
    answer = ''
    str.each_char do |c| answer += map[c] end
    answer
  end
  private_class_method :convert
end
