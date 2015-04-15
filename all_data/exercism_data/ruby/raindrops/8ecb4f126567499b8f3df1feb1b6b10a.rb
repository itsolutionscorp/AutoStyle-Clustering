class Raindrops
  def self.convert(n)
    output = ''
    if (n % 3 == 0)
      output << 'Pling'
    end
    if n % 5 == 0
      output << 'Plang'
    end
    if n % 7 == 0
      output << 'Plong' 
    end
    output == '' ? n.to_s : output
  end
end
