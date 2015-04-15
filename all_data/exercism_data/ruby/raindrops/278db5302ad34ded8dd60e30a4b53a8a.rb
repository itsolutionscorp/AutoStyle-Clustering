class Raindrops
  def self.convert(n)
    ret = ''
    
    if n % 3 == 0
      ret += 'Pling'
    end
    
    if n % 5 == 0
      ret += 'Plang'
    end
    
    if n % 7 == 0
      ret += 'Plong'
    end

    if ret.length == 0
      ret = n.to_s
    end

    ret
    
  end
end
