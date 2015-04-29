class Hamming
  def self.compute(first,second)
    distance = 0
    first.chars.to_a.each_with_index do |value,i|
      distance += 1 unless value == second[i]
    end
    distance
  end
end
