class Hamming
  def self.compute(str1, str2)
  	a = str1.to_s.split('').zip(str2.to_s.split(''))
  	b = 0

  	a.collect do |v| #[0].include?(v[1]) }
    	if v[0] != v[1] && v[1] != nil
    		b += 1
   		end
   	end
    return b
	end
end