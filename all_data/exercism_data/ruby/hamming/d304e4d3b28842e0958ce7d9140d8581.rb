module Hamming
  module_function

  def compute strand_a, strand_b
    diff = 0
    strand_a.split('').each_with_index do |code, index|
      diff += 1 if strand_b[index] && code != strand_b[index]
    end
    diff
  end

end
