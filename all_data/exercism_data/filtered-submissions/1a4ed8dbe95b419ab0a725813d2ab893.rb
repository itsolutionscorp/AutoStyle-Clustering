def compute(stringA, stringB)
        min = [stringA.length, stringB.length].min
        count = 0
        min.times { |i|
            count = count+1 if (stringA[i] != stringB[i])
        }
        count
    end