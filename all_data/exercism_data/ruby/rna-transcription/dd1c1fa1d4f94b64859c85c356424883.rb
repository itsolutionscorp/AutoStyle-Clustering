class Complement
@complement_map = {"G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  def self.of_dna(dna)
    converted = []
    dna.chars.each do |i| converted << @complement_map[i] end    
    converted.join('')
  end

  def self.of_rna(rna)
    reversed_complement_map = @complement_map.invert
    converted = []
    rna.chars.each do |i| converted << reversed_complement_map[i] end
    converted.join('')
  end
end
