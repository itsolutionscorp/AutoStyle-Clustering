class Raindrops
  def self.convert(num)
    sounds_for_num(num) || num.to_s
  end

  def self.sounds_for_num(num)
    sounds = ''
    sounds << 'Pling' if num % 3 == 0
    sounds << 'Plang' if num % 5 == 0
    sounds << 'Plong' if num % 7 == 0
    sounds unless sounds.empty?
  end
  private_class_method :sounds_for_num
end
