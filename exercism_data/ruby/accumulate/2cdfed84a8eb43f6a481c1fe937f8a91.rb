class Array
	def accumulate
		r = []
		each do |e|
			r << (yield e)
		end
		r
	end
end
