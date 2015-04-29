class Hamming

    def self.compute(a,b)
      a,b = [a,b].sort { a.length <=> b.length }
      a.chars.zip(b.chars).reduce(0) do |memo, a|
        if a[0] != a[1]
          memo = memo + 1
        end
        memo
      end
    end

end
