class Raindrops

  def self.convert(n)
    c = ->(f,w) { n % f == 0 && w || '' }
    o = c.(3, 'Pling') + c.(5, 'Plang') + c.(7, 'Plong')
    o.length == 0 ? n.to_s : o
  end

end
