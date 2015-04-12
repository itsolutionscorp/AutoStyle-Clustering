class Hamming
  def compute(letterA,letterB)
    i = 0
    diff = 0
    letterA.each_char do |c|
       if !c.eql?(letterB[i])
          diff = diff + 1
       end
       i = i + 1
    end
    return diff
  end
end
