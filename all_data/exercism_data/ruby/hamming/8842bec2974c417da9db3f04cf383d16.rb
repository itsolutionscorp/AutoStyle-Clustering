class Hamming

  def self.compute sequence1, sequence2

    if sequence1.length <= sequence2.length
      count_differences sequence1, sequence2
    else
      count_differences sequence2, sequence1
    end

  end

private

  def self.count_differences text1, text2
    count = 0

    text1.chars.each_index do |index|
      count += 1 if text1[index] != text2[index]
    end

    count
  end
end
