class Hamming

  def self.compute(arg1, arg2)
    diff = 0

    (0..arg1.length).each do |i|
        diff +=1 if arg1[i] != arg2[i]
    end

    return diff
  end
end
