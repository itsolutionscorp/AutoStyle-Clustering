module Raindrops
  def self.convert(n)
    r_speak = ""
    r_speak += "Pling" if n % 3 == 0
    r_speak += "Plang" if n % 5 == 0
    r_speak += "Plong" if n % 7 == 0
    r_speak += n.to_s if r_speak.empty?
    r_speak
  end
end
