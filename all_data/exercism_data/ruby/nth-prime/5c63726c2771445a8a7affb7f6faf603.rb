class Prime
  def self.nth(n)
    if n <= 0 or not n.is_a? Integer
      raise ArgumentError
    end
    #kind of slow without an upperbound on n
    a = [2, 3, 5, 7, 11, 13]
    z = 17
    until a.length >= n 
      found = true
      for x in a
        if z % x == 0
          found = false
          break
        end
      end
      
      if found
        a.push z
      end
      z += 2
    end
    a[n - 1]
  end
end
