class Raindrops
  def self.convert(num)
    sounds = ''
    sounds << 'Pling' if num % 3 === 0
    sounds << 'Plang' if num % 5 === 0
    sounds << 'Plong' if num % 7 === 0
    sounds.empty? ? num.to_s : sounds
  end
end
