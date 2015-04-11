class Raindrops

  NUMBER = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(number)
    str = ''

    NUMBER.each{|key, value| str += value if number % key == 0  }

    if str.empty?
      number.to_s
    else
      str
    end

  end  

end
