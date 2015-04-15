require "pry"
class Hamming
  def self.compute(first, second)
    if first.size == second.size
      (0..first.size - 1).inject(0) do |distance, index|
        first[index] != second[index] ? distance + 1 : distance
      end
    end
  end
end
