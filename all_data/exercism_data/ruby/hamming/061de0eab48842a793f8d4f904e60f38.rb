class Hamming
	class << self
		def compute(s, t)
			s_arr, t_arr = s.split(""), t.split("")
			s_arr.each_with_index.map do |a,i|
				same?(a, t_arr[i])					
			end.inject{|sum,x| sum+x }
		end

		def same?(a1, a2)
			(a2.nil? or a1.eql?(a2)) ? 0 : 1
		end
	end
end
