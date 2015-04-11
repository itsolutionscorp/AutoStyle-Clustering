class Hamming

  def self.compute(first_strand, second_strand)
    first_strand.chars.each_with_index.count do |letter, index|
      second_strand[index] != letter
    end
  end

end
