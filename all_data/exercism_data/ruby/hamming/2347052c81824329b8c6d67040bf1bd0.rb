class Hamming

  def self.compute(string_1, string_2)
    length_comparison = string_1.length <=> string_2.length

    string_1 = string_1[0...string_2.length] if length_comparison == 1
    string_2 = string_2[0...string_1.length] if length_comparison == -1

    hamming_count = 0

    (0..string_1.length).each do |n|
      hamming_count += 1 if string_1[n] != string_2[n]
    end

    hamming_count
  end

end
