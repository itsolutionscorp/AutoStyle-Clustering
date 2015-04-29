class Grains
	CHESSBOARD_SIZE=64
	@@squares=Hash.new{|h,k|h[k]=(2**(k-2)*2)}
	def square(x)
		@@squares[x]
	end
	def total
		CHESSBOARD_SIZE.times.inject(0){|s,o|s=s+@@squares[o+1]}
	end
end
