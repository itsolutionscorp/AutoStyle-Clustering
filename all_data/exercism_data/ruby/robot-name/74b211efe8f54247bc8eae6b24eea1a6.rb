class Robot
	def name
		num = Random.new.rand(1001..1999).to_s.split(//).last(3).join
		char1 = (Random.new.rand(26)+64).chr
		char2 = (Random.new.rand(26)+64).chr
		char1+char2+num
	end
end
