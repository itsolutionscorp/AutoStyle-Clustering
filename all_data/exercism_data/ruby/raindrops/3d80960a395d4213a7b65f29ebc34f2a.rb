class Raindrops
  def self.convert i
    s = new.drops(i)
    s.empty? ? i.to_s : s
  end

  def drops i
    @i = i
    "" << pling? << plang? << plong? 
  end

  def pling?
    @i % 3 == 0 ? "Pling" : "" 
  end

  def plang?
    @i % 5 == 0 ? "Plang" : ""
  end

  def plong?
    @i % 7 == 0 ? "Plong" : ""
  end
end
