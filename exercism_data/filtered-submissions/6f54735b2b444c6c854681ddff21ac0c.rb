class Hamming
	def compute(x,y)
		_score = 0
		if x.length != y.length
			puts "You are comparing differently lengthed strings, please try again."
			exit
		end
		_i = 0
		_z = x.length
		_score = 0
		while _i < _z do
			if x[_i] != y[_i]
				_score +=1
			end
			_i += 1
		end
		_score
	end
end
