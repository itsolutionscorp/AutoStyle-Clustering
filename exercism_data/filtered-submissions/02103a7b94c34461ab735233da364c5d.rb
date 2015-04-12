class Hamming
  def compute(str1, str2)
    diff = 0

    str1.split('').size.times do |index|
      next if str1[index].nil? or str2[index].nil?
      diff += 1 if str1[index] != str2[index]
    end

    diff
  end
end
