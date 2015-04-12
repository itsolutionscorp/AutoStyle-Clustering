class Hamming
  def compute(dna_1, dna_2)
    dna_1.split('').each_with_index.inject(0) do |num_diff,(char,i)|
      break num_diff unless dna_2[i]
      char == dna_2[i] ? num_diff : num_diff + 1
    end
  end
end
