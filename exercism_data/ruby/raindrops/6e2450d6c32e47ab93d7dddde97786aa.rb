# raindrops.rb
class Raindrops
  DROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  def self.convert(n)
    DROPS.each_with_object('') { |d, s| s << d[1] if n % d[0] == 0 }[/.+/] || n.to_s
  end
end
