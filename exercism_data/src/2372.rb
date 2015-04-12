class Hamming
  def compute(input1, input2)
    diff = 0
    min_length = [input1.length, input2.length].min - 1

    for i in 0..min_length do
      diff += 1 unless input1[i] == input2[i]
    end

    diff
  end
end
