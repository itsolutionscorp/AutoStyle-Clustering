module Hamming
  def compute str1, str2
    distance = 0

    str1.chars.each_with_index do |c,i|
      next if str2[i].nil?
      distance += 1 unless c == str2[i]
    end

    distance
  end
end
