class Hamming
  def self.compute(str1, str2)
    count = 0
    str1.split("").each_with_index do |s, i|
      break if str2[i].nil? || s.nil?
      count= count+1 if s != str2[i]
    end
    count
  end
end
