class Hamming
  def self.compute(stringA, stringB)
    maxlen = [stringA.length, stringB.length].min
    maxlen.times.count{ |i| stringA[i] != stringB[i] }
  end
end
