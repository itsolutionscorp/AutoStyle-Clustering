class Hamming

	def initialize

	end

	def self.compute(stringone,stringtwo)

		string_one_set=stringone.chars
		string_two_set=stringtwo.chars

		if stringone.length > stringtwo.length
			shortlength = stringtwo.length
		else
			shortlength = stringone.length
		end

		index_point =0
		mismatch = 0

		shortlength.times do
			if string_one_set[index_point] != string_two_set[index_point]
				mismatch+=1
			else 
			end
			index_point +=1
		end
		return mismatch
	end
end



		# stringone=stringone.to_s.downcase
		# stringtwo=stringtwo.to_s.downcase

		# if stringone.length > stringtwo.length
		# 	shortlength = stringtwo.length
		# else
		# 	shortlength = stringone.length
		# end

		# string_one_list={"a" => 0, "t" => 0, "g" => 0, "c" => 0}
		# string_two_list={"a" => 0, "t" => 0, "g" => 0, "c" => 0}
		# set_one = string_one_list.map {|key,value| value=stringone[0,shortlength].count(key)}
		# set_two = string_two_list.map {|key,value| value=stringtwo[0,shortlength].count(key)}
		
		# p "======================="
		# p stringone
		# p stringtwo

		# p "set_one",set_one
		# p "set_two",set_two
		# p "shortlength",shortlength
		# p "======================="

		# delta_a = set_one[0]-set_two[0]
		# delta_t = set_one[1]-set_two[1]
		# delta_g = set_one[2]-set_two[2]
		# delta_c = set_one[3]-set_two[3]

		# result = delta_a.abs+delta_t.abs+delta_g.abs+delta_c.abs
		
		# p "======================="
		# p "delta_a", delta_a

		# return result/2 

		# end
