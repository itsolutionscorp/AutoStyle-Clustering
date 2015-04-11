class Raindrops
  RAINSPEAK = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  def self.convert(drop)
    rainsound = ""
    RAINSPEAK.each do |prime,noise|
     rainsound << noise if drop % prime == 0 
    end
    rainsound.empty? ? drop.to_s : rainsound
  end
end


