class Raindrops

  RAINDROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(num)
    output = ''

    RAINDROPS.each { |factor, drop|
      output += drop if(num % factor == 0)
    }

    (output.length == 0) ? num.to_s : output
  end

end
