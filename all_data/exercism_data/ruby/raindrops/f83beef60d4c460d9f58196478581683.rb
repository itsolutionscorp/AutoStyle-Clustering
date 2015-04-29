class Raindrops
  def self.convert(num)
    ans = ""
    ans << "Pling" if num % 3 == 0
    ans << "Plang" if num % 5 == 0
    ans << "Plong" if num % 7 == 0
    return num.to_s if ans.empty? 
    ans
  end
end
