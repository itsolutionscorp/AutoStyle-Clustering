module Hamming
  def self.compute(first, second)
    (0..first.length).inject(0) do |distance, index|
      distance += 1 unless first[index] == second[index]
      distance
    end
  end
end
