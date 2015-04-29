class Raindrops

  TRANSLATE = {
  	3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    message = ''

    TRANSLATE.each do |(numb, phrase)|
      message << phrase if number % numb == 0
    end

    if message.empty? then number.to_s else message end
    
  end
end
