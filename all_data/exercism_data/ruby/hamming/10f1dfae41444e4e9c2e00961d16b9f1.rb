class Hamming
  def self.compute(first_strand, second_strand)
    (0...second_strand.length).count do |index|
      second_strand[index] != first_strand[index]
    end
  end
end
