class Raindrops
  @@Mappings = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(x)
    s = @@Mappings.select {|factor, string| x % factor == 0}.values.join
    s.length == 0 ? x.to_s : s
  end
end
