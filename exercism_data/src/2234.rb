class Hamming
  def compute(str1, str2)
    hamming_number = 0
    str1.chars.each_with_index.each do |ch, idx|
      hamming_number = hamming_number + 1 if ch != str2[idx]
    end
    hamming_number
  end
end
