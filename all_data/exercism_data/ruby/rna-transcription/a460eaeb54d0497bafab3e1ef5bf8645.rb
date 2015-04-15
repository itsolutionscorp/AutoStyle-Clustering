class Complement
 
  @@dna = { 'C' => 'G' , 'G' => 'C' , 'T' => 'A' , 'A' => 'U' }
  @@rna = { 'C' => 'G' , 'G' => 'C' , 'U' => 'A' , 'A' => 'T' }
  class << self
    def of_dna(s)
      s.chars.inject("") do |acc,c| 
        acc << @@dna[c]
      end
    end

    def of_rna(s)
      s.chars.inject("") do |acc,c| 
        acc << @@rna[c]
      end
    end
  end
end
