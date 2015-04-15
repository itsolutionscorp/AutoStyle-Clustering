class Hamming
  def self.compute(val1, val2)
    val1 = val1.split(//)
    val2 = val2.split(//)
    ret = 0
    val1.each_with_index do |v, i|
      if(v != val2[i])
        ret += 1
      end
    end
    ret
  end
end
