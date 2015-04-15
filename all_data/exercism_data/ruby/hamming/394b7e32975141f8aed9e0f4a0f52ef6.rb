class Hamming
  def self.compute(dna_one,dna_two)
    smallest = [dna_one.size,dna_two.size].min
    (0...smallest).count do |i|
      dna_one[i] != dna_two[i]      
    end
  end
end
