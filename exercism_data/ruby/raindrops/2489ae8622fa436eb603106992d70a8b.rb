class Raindrops<Prime 

	def self.convert(num)
		pf = Prime.prime_division(num).flatten.sort.uniq.delete_if {|i| i<3 || i>7 }
		if pf.include?(3) || pf.include?(5) || pf.include?(7)
			pf.to_s.gsub(/[357\W]/, '3' => 'Pling', '5' => 'Plang','7' => 'Plong')
		else
			num.to_s
		end
	end
end
