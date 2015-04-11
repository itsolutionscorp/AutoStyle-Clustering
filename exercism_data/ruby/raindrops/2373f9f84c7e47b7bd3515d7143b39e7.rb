class Raindrops
  def self.convert i
    fail unless i.is_a? Fixnum
    str = ""
    str << "Pling" if i % 3 == 0
    str << "Plang" if i % 5 == 0
    str << "Plong" if i % 7 == 0
    str.empty? ? i.to_s : str
  end
end
