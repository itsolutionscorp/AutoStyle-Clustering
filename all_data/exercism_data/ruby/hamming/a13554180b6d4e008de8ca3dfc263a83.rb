class Hamming
  def initialize
  end

  def self.compute(first,second)
    if first.length()==0 or second.length() == 0
      return -1
    else
      if first == second
	return 0
      else
	lgt = first.length()
	if second.length()<lgt
	  lgt = second.length()
	end
	cnt = 0
	for i in 0..(lgt-1)
	  if first[i] != second[i]
	    cnt += 1
	  end
	end 
	return cnt
      end
    end
  end
end
