class Hamming
  def compute(dna1, dna2)
	count = 0	
	dna1.split("").each_with_index do |str,i|
	  count = count + 1 unless str == dna2[i] unless  i > dna2.length - 1
	end

	count
  end
end
