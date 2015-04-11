class Raindrops

  MODULO_MAP = {3 => 'i', 5 => 'a', 7 => 'o'}

  def self.convert(num)
    sound = create_onomatopoeia(num)
    sound.empty? ? num.to_s : sound
  end

  def self.onomatopoeia(letter)
    ['Pl', 'ng'].insert(1,letter).join
  end

  def self.create_onomatopoeia(num)
    MODULO_MAP.collect do |d, l|
      onomatopoeia(l) if num % d == 0
    end.join
  end
end
