class Raindrops
  PARTS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(i)
    s = PARTS.keys.map {|k| PARTS[k] if i % k == 0} .join
    s.empty? ? i.to_s : s
  end
end
