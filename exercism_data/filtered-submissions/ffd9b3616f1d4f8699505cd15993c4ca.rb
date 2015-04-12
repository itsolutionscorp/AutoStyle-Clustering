class Hamming
  def compute(str1, str2)
    return 0 if str1 == str2
    dist = 0
    short_len = str1.length > str2.length ? str2.length : str1.length
    short_len.times do |i|
      dist += 1 unless str1[i] == str2[i]
    end
    dist
  end
end
