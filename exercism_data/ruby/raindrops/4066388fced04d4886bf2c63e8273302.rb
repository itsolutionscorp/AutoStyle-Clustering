class Raindrops
  def self.convert(n)
    result = ''
    if 0 == n%3 
      result += 'Pling'
    end
    if 0 == n%5
      result += 'Plang'
    end
    if 0 == n%7
      result += 'Plong'
    end
    if '' == result
      result = n.to_s
    end
    return result
  end
end
