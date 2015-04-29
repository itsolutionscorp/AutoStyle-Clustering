class Hamming
  def self.compute(str1, str2)
    str1.chars.each_with_index.reduce(0) do |diff, (v,i)|
      diff += mutation?(str1, str2, i) ? 1 : 0
    end
  end

  private

  def self.mutation?(str1, str2, index)
  	return false if str2[index].nil?
  	str1[index] != str2[index]
  end
end
