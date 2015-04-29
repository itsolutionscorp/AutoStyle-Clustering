require 'pp'
#require 'pry'


class Hamming

	#def initialize hm1, hm2
	#	@hm1 = hm1
	#	@hm2 = hm2
	#end

	
	def self.compute (arg1, arg2)
		(0...[arg1.length, arg2.length].min).count { |i| arg1[i] != arg2[i]	 }
	end

end


#h = Hamming.new "GAGCCTACTAACGGGAT", "CATCGTAATGACGGCCT"
#pp Hamming.compute "GAGCCTACTAACGGGAT", "CATCGTAATGACGGCCT"
