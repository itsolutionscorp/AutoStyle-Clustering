class Complement
  @@d_to_r = {'G'=> 'C', 'C'=> 'G', 'T'=>'A', 'A'=>'U'}
  @@r_to_d = {'G'=> 'C', 'C'=> 'G', 'U'=>'A', 'A'=>'T'}

  def self.of_dna(dna)
    output = ""
    dna.each_char do |char|
      output << @@d_to_r[char]
    end
    return output
  end

  def self.of_rna(rna)
    output = ""
    rna.each_char do |char|
      output << @@r_to_d[char]
    end
    return output
  end
end
