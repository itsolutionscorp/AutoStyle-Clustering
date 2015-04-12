class Hamming
  def compute(s1, s2)
    result = 0

    size = [s1.length, s2.length].min
    # determining the length of the shortest string

    size.times do |i|
      if s1[i] != s2[i]
        result += 1 #add a mismatch to the total count
      end
    end

    result
  end
end
