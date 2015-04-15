class Raindrops
  def self.convert(a)
    return a.to_s unless pling(a) || plang(a) || plong(a)
    [pling(a), plang(a), plong(a)].join
  end

  def self.pling(a)
    return 'Pling' if a % 3 == 0
  end

  def self.plang(a)
    return 'Plang' if a % 5 == 0
  end

  def self.plong(a)
    return 'Plong' if a % 7 == 0
  end
end
