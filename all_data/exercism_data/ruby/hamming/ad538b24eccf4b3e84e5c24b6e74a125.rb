class Hamming
  def self.compute(str1 ,str2)
    index, diff_count = 0, 0

    # ignore extra length on first strand
    str1 = str1[0...str2.length] if str2.length < str1.length

    str1.each_char do |char|
      diff_count += 1 if char != str2[index]
      index += 1
    end

    diff_count
  end
end
