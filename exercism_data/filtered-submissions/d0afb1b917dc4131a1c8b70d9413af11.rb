class Hamming
  def compute(str1, str2)
    side_by_side_letters = str1.chars.zip(str2.chars)
    side_by_side_letters.count { |letter| letter[0] == letter[1] }
  end
end
