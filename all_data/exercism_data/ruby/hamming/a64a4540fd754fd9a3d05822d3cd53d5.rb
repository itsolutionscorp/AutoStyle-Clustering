class Hamming
  def self.compute first_strand, second_strand
    differnces = 0
    first_strand.split('').each_with_index do |char, i|
      differnces += 1 unless char == second_strand[i]
    end
    differnces
  end
end
