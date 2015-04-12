class Hamming
  def compute(word1, word2)
    distance = 0
    word1.split('').each_with_index do |char, i|
      distance += 1 unless char.eql? word2[i]
    end
    distance
  end
end
