class Complement

  DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA = DNA.invert

  def self.of_rna(keys)
    keys.each_char.collect{|k| RNA[k]}.join('')
  end
  
  def self.of_dna(keys)
    keys.each_char.collect{|k| DNA[k]}.join('')
  end

end
