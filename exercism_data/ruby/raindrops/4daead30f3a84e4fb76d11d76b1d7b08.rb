class Raindrops
  def self.convert(number)
    outstring = ''
    skipnumbers = false
    if number % 3 == 0
      outstring += 'Pling'
      skipnumbers = true
    end
    if number % 5 == 0
      outstring += 'Plang'
      skipnumbers = true
    end
    if number % 7 == 0
      outstring += 'Plong'
      skipnumbers = true
    end
    if skipnumbers == false
      outstring = "#{number}"
    end
    return outstring
  end
end
