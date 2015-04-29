class Raindrops

  def self.convert(nbr)
    plout = plonger(nbr)
    plout.empty? ? "#{nbr}" : plout
  end

  def self.plonger(nbr)
    '' << pling(nbr) << plang(nbr) << plong(nbr)
  end

  def self.pling(nbr)
    return 'Pling' if (nbr % 3).zero?
    ''
  end

  def self.plang(nbr)
    return 'Plang' if (nbr % 5).zero?
    ''
  end

  def self.plong(nbr)
    return 'Plong' if (nbr % 7).zero?
    ''
  end

end
