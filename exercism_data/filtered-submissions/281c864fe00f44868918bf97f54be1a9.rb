class Hamming

  def compute(strandOne, strandTwo)
    sum = 0

    strandOne.chars.each_with_index do |c,i|
      (sum += 1) unless c == strandTwo[i]
    end

    sum
  end
end
