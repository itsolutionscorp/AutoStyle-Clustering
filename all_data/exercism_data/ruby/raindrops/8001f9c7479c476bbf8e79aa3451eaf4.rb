module Raindrops
  def self.convert n
    outp = ""
    outp << 'Pling' if n % 3 == 0
    outp << 'Plang' if n % 5 == 0
    outp << 'Plong' if n % 7 == 0
    return n.to_s if outp == ""
    outp
  end
end
