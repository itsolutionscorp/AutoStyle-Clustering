class Hamming
  def self.compute(str1 = "", str2 = "")
    sequence_1, sequence_2, distance = str1.chars, str2.chars, 0

    sequence_1.zip(sequence_2).each do |n1, n2|
      distance += 1 unless (n1 == n2) or n2.nil?
    end

    distance
  end
end
