class Hamming
  def self.compute(case1, case2)
    count = 0
    for x in (0..case2.length-1)
      if x > case1.length - 1
        break
      elsif case1[x].chr != case2[x].chr
        count += 1
      end
    end
    count
  end
end
