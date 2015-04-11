class Raindrops
	# @@special_nums = [3,5,7]
	# @@conversions = {3 => "Pling", 5 => "Plang", 7=> "Plong"}

	# def self.convert (int)
	# 	result = ""

	# 	@@special_nums.each {|num|
	# 		if int % num == 0

	# 			while int % 2 == 0
	# 				int = int / 2
	# 			end
				
	# 			result = result + @@conversions[num]
	# 			while int % num == 0
	# 				int = int / num
	# 			end
	# 		end
	# 	}

	# 	if result == ""
	# 		int.to_s
	# 	else
	# 		result
	# 	end
	# end

	def self.convert (n)
		result = ""
		conversions = {3 => "Pling", 5 => "Plang", 7=> "Plong"}

		conversions.each {|k, v|
			n % k == 0 ? result += v : nil
		}

		result == "" ? n.to_s : result
	end

end
