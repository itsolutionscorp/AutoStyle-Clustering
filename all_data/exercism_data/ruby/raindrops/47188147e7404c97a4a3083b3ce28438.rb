class Raindrops

  def self.convert(string)
    output = ''
    if string % 3 == 0
      output << 'Pling'
    end
    if string % 5 == 0
      output << 'Plang'
    end
    if string % 7 == 0
      output << 'Plong'
    end
    if output.length == 0
      output << string.to_s
    end
    output
  end

end
