class Hamming

  def compute(dna1, dna2)
    mutations = 0

    dna1.chars.each_with_index do |x, index|
      unless x.nil? || dna2[index].nil?
        mutations += 1 if x != dna2[index]
      end
    end

    mutations
  end

end
