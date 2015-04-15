class Raindrops
  def self.convert(x)
    num = x.to_int
    string = ''
    if num%3 == 0
      string += 'Pling'
    end
    if num%5 == 0
      string += 'Plang'
    end
    if num%7 == 0
      string += 'Plong'
    end
    if num%3 != 0 && num%5 != 0 && num%7 != 0
      string = x.to_s
    end

    string

  end
end
