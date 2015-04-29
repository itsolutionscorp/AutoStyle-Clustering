class Raindrops

  SOUNDS =  {
              3 => "Pling",
              5 => "Plang",
              7 => "Plong"
            }


  def self.convert(number)
    string = ''
    SOUNDS.each {|prime, sound| string += sound if number % prime == 0 }
    string.empty? ? number.to_s : string
  end

end
