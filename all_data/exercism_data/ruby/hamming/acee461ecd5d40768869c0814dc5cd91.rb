class Hamming

  def self.compute(dna1, dna2)
    mistakes = 0

    dna1.chars.each_with_index do |x, index|
      unless x.nil? || dna2[index].nil?
        mistakes += 1 if x != dna2[index]
      end
    end

    mistakes
  end

end
