class Raindrops
  @@d = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(i)
    s = @@d.keys.map {|k| @@d[k] if i % k == 0} .join
    return s.empty? ? i.to_s : s
  end
end
