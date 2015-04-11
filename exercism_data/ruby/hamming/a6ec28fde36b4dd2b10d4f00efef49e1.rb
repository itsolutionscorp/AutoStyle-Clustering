class Hamming
	def self.compute(argument1, argument2)

		index = argument1.length - 1
		how_many = 0
		if argument1.length != argument2.length
			puts "Sorry, I can't compare these strands"
		end
		if argument1.length == 1 && argument1[0] == argument2[0]
			return 0
		else
			for i in 0..index
				if argument1[i] != argument2[i]
					how_many += 1
				end
			end
			return how_many
		end
	end
end


# Don't quite understand, but it's a pretty cool implementation
# class Hamming
#   def self.compute(a , b)
#     (0...[a.length, b.length].min).count do |i|
#       a[i] != b[i]
#     end
#   end
# end

Hamming.compute('ABC','ACB')
