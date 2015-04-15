class Raindrops

    DROPS ={
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

  def self.convert(number)
    output = ""
    DROPS.keys.sort.each do |factor|
      if number.modulo(factor) == 0
        output << DROPS[factor]
        number = number / factor
      end
    end
    if output.empty?
      output = number.to_s
    end
    output
  end
end
