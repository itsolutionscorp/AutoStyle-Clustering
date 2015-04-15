class Complement
  DNA_MAP = { 
    'G'=>'C',
    'C'=>'G',
    'A'=>'U',
    'T'=>'A',
  }
  RNA_MAP = {
    'G'=>'C',
    'C'=>'G',
    'A'=>'T',
    'U'=>'A',
  }
  def self.complement_map(string,map)
    string.each_char.reduce('') do |collection, allele|
      collection += map[allele]
    end
  end

  def self.of_dna(arg)
    self.complement_map(arg,DNA_MAP)
  end
  
  def self.of_rna(arg)
    self.complement_map(arg,RNA_MAP)
  end
end
