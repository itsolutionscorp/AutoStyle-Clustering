class Hamming
  def self.compute(stringA, stringB)
    differences = 0
    maxlen = [stringA.length, stringB.length].min
    maxlen.times do |i|
      if stringA[i] != stringB[i]
        differences += 1
      end
    end
    return differences
  end
end
