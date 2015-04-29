class Raindrops
  SOUNDS = {2 => '',3 => 'Pling',5 => 'Plang',7 => 'Plong'}

  def self.convert(num)
    rain_sound = ''
    original_num = num
    SOUNDS.each do |prime,sound|
      rain_sound += sound if (num % prime).zero?
      num = num / prime while (num % prime).zero?
    end
    rain_sound == '' ? original_num.to_s : rain_sound
  end
end
