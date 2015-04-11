class Raindrops
  def self.convert(n)
    s = "#{'Pling' if n%3 == 0}#{'Plang' if n%5 == 0}#{'Plong' if n%7 == 0}"
    s.empty? ? n.to_s : s
  end
end
