module Prime
  @@cache = {1 => 2}

  def self.nth(n)
    raise ArgumentError if n <= 0 or n.class != Fixnum
    return @@cache[n] if @@cache.has_key? n
    s = @@cache.keys.max
    pno = @@cache[s] + 1
    while s < n
      m = 1
      while m <= s
        if pno % @@cache[m] == 0
          pno += 1
          m = 1
        else
          m += 1
        end
      end
      @@cache[s+1] = pno
      s += 1
    end
    @@cache[n]
  end
end
