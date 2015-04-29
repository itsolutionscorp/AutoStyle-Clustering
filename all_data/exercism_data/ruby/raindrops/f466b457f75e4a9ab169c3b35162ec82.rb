class Raindrops

  def self.convert(n)
    res = n.to_s
    res += "Pling" if n % 3 == 0
    res += "Plang" if n % 5 == 0
    res += "Plong" if n % 7 == 0
    if res =~ /\D/
      res.match(/\D+/).to_s
    else
      n.to_s
    end
  end

end
