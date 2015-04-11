class Raindrops
  @@sounds = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    
  def self.convert(num)
    sound = @@sounds.map{|n,s| (num % n == 0) ? s : ''}.join
    (sound == '') ? num.to_s : sound
  end
end
