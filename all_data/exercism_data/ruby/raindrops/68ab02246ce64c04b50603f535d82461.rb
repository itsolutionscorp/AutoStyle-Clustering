class Raindrops
  def self.convert(num)
    str = "#{pling(num)}#{plang(num)}#{plong(num)}"
    str.empty? ? num.to_s : str
  end

  def self.pling(num)
    make_sound('Pling', num, 3)
  end

  def self.plang(num)
    make_sound('Plang', num, 5)
  end

  def self.plong(num)
    make_sound('Plong', num, 7)
  end

  def self.make_sound(sound, num, divisor)
    sound if (num % divisor).zero?
  end
end
