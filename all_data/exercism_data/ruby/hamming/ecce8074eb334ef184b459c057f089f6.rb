class Hamming
    def self.compute(a, b)
        stringA = a.split("")
        stringB = b.split("")
        c = stringA.length < stringB.length ? stringA.length : stringB.length
        result = 0
        c.times do |i|
            result += stringA[i] != stringB[i] ? 1 : 0
        end
        result
    end
end
