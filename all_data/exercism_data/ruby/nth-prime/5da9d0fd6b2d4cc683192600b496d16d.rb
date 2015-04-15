class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    table = [true]*(2*n*Math.log(n) + 3)
    (2..table.size).each do |i|
      if table[i]
        n -= 1
        return i if n == 0
        mark = i*i
        while mark < table.size
          table[mark] = false
          mark += i
        end
      end
    end
  end
end
