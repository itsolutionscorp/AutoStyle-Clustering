class Raindrops
  def self.convert(i)
    out = ''
    find = false
    if i % 3 == 0
      out += 'Pling'
      find = true
    end
    if i % 5 == 0
      out += 'Plang'
      find = true
    end
    if i % 7 == 0
      out += 'Plong'
      find = true
    end
    return find ? out : i.to_s()
  end
end
