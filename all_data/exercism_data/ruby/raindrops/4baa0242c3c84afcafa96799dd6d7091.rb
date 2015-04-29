class Raindrops
  def self.convert(n)
    res = ""
    if n % 3 == 0 then
      res += "Pling"
    end
    if n % 5 == 0 then
      res += "Plang"
    end
    if n % 7 == 0 then
      res += "Plong"
    end
    if res.empty? then
      return n.to_s
    end
    return res
  end
end
