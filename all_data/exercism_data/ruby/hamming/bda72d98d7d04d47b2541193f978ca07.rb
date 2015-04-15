module Hamming

  class << self

    def compute(a, b)
      distance = 0
      distance +=1 if a[0] != b[0]
      distance += a.size == 0 ? 0 : compute(tail(a),tail(b))
    end

    private

    def tail(string)
      string[1..-1]
    end

  end

end
