class Raindrops

  def self.convert(n)
    s = ''

    if n % 3 == 0
      s += 'Pling'
    end

    if n % 5 == 0
      s += 'Plang'
    end

    if n % 7 == 0
      s += 'Plong'
    end

    if s.empty?
      s = n.to_s
    end

    return s
  end
end
