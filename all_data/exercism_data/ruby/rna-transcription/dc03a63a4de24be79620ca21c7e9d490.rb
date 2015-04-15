class Complement
 
  DNA_TO_RNA = { 'C' => 'G' , 'G' => 'C' , 'T' => 'A' , 'A' => 'U' }.freeze
  RNA_TO_DNA = { 'C' => 'G' , 'G' => 'C' , 'U' => 'A' , 'A' => 'T' }.freeze
  class << self
    def of_dna(s)
      s.chars.inject("") do |acc,c| 
        acc << DNA_TO_RNA[c]
      end
    end

    def of_rna(s)
      s.chars.inject("") do |acc,c| 
        acc << RNA_TO_DNA[c]
      end
    end
  end
end
