class Hamming
  def self.compute(stringA, stringB)
    maxlen = [stringA.length, stringB.length].min
    diff = maxlen.times.reduce(0) do |differences, i|
      differences += 1 if stringA[i] != stringB[i]
      differences
    end
  end
end
