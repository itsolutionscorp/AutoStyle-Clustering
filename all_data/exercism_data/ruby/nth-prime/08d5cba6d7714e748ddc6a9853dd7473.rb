require 'prime'
class Prime
	def self.nth test_num
		if test_num == 0 then raise ArgumentError, "Suck a Duck"
		end
		Prime.take(test_num).last
	end
end
