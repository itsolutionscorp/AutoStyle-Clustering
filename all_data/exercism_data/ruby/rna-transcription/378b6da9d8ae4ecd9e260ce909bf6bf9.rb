class Complement

  def self.of_dna(arg)
    match_hash_dna = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'U',
      'T' => 'A'
    }
    arg = arg.split("")
    result = ''
    arg.each do |elem| 
      result += match_hash_dna[elem]
    end
    result
  end

  def self.of_rna(arg)
    match_hash_rna = {
      'C' => 'G',
      'G' => 'C',
      'U' => 'A',
      'A' => 'T'
    }
    arg = arg.split("")
    result = ''
    arg.each do |elem| 
      result += match_hash_rna[elem]
    end
    result
  end
end
