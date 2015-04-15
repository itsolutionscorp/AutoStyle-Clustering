class Raindrops

  def self.convert(int)
  	ans = ""
  	ans << "Pling" if int % 3 == 0		
  	ans << "Plang" if int % 5 == 0
  	ans << "Plong" if int % 7 == 0
  	ans = int.to_s if ans.empty?
  	ans
  end
end
