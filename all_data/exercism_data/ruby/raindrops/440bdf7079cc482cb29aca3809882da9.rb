class Raindrops
  def self.convert(number)
    outstring = ''
    if number % 3 == 0
      outstring << 'Pling'
    end
    if number % 5 == 0
      outstring << 'Plang'
    end
    if number % 7 == 0
      outstring << 'Plong'
    end
    if outstring.chomp == ''
      outstring = number.to_s
    end
    outstring
  end
end
