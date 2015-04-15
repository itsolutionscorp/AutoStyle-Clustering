class Raindrops
  def self.convert(num)
    (s = not_1("", num)) == "" ? num.to_s : s
  end
  
  def self.not_1(s, num)
    { 3 => "Pling", 5 => "Plang", 7 => "Plong" }.each do |key, val|
      s << val if num % key == 0
    end
    s
  end
  
end
