class Raindrops
  def self.convert(n)
    sound = ''
    sound << 'Pling' if pling(n)
    sound << 'Plang' if plang(n)
    sound << 'Plong' if plong(n)

    sound.empty?? n.to_s : sound
  end

  def self.pling(n)
    n % 3 == 0
  end

  def self.plang(n)
    n % 5 == 0
  end

  def self.plong(n)
    n % 7 == 0
  end
end
