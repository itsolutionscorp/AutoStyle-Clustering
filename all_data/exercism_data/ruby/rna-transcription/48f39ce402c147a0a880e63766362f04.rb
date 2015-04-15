class Complement
  
  COMPLIMENTS = { "G" => "C", "C" => "G" ,"T" => "A" , "A" => "U" }
  
  def self.of_dna nuc
    iterate!(nuc, COMPLIMENTS.method(:[]))
  end

  def self.of_rna nuc
    iterate!(nuc, COMPLIMENTS.method(:key))
  end

  def self.iterate! nuc, method 
    nuc.chars.each_with_index { |n, i| nuc[i] = method.call(n) }
    return nuc
  end

end
