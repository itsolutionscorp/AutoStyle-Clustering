class Hamming
  def self.compute(str1, str2)
    shorter_strand = [str1,str2].min_by(&:length)
    (0...shorter_strand.length).select{|num|
      str1[num] != str2[num]
    }.length
  end
end
