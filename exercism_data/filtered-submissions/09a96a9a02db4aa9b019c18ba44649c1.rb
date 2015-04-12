class Hamming

    def compute(dA, dB)
        dA.each_char.zip(dB.each_char).map { |x| x[0] == nil or x[1] == nil or x[0] == x[1] }
                                      .reduce(0) { |m,o| o == false ? m+1 : m+0 }
    end
end
