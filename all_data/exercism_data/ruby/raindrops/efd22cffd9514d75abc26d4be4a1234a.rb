class Raindrops
  def self.convert(number)
    ret = number % 3 == 0 ? "Pling" : ""
    ret += number % 5 == 0 ? "Plang" : ""
    ret += number % 7 == 0 ? "Plong" : ""
    ret = ret == "" ? number.to_s : ret
  end
end
