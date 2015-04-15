class Raindrops
  def self.convert(drops)
    sound  = ''
    sound << 'Pling'    if drops % 3 == 0
    sound << 'Plang'    if drops % 5 == 0
    sound << 'Plong'    if drops % 7 == 0
    sound  = drops.to_s if sound.empty?
    sound
  end
end
