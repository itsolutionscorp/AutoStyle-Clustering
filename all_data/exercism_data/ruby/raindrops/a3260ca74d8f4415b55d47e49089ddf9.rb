class Raindrops
  def Raindrops.convert (n)
    out = ""
    out += "Pling" if (n % 3).zero?
    out += "Plang" if (n % 5).zero?
    out += "Plong" if (n % 7).zero?
    out.size > 0 ? out : n.to_s
  end
end
