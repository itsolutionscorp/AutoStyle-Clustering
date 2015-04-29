class Hamming
  def self.compute(arg1, arg2)
    unless arg1.length == arg2.length
      puts "DNA strands are not of equal length."
      0
    else
      hammDis = 0
      for i in 0...arg1.length
	hammDis+=1 unless arg1[i] == arg2[i]
      end
      hammDis
    end
  end
end
