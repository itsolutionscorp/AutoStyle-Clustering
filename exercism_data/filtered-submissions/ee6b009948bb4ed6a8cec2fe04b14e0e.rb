class Hamming
  def compute(dna_one,dna_two)
    total = 0
    dna_one.split('').each_with_index do |value,index|
      break if dna_two[index].nil?
      total+=1 if value != dna_two[index]
    end
    total
  end
end
