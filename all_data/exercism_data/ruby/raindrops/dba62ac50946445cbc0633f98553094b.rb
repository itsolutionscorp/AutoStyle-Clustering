class Raindrops
  def self.convert num
  	ret = ''
  	if num >= 3 then
      ret += 'Pling' if (num % 3) == 0
      ret += 'Plang' if (num % 5) == 0
      ret += 'Plong' if (num % 7) == 0
    end
    ret.empty? ? num.to_s : ret
  end
end
