class Raindrops

  def self.convert(num)
    unless num % 3 == 0 || num % 5 == 0 || num % 7 == 0
      return num.to_s
    end
    sound = ''
    num % 3 == 0 ? sound << 'Pling' : sound
    num % 5 == 0 ? sound << 'Plang' : sound
    num % 7 == 0 ? sound << 'Plong' : sound
  end

end
