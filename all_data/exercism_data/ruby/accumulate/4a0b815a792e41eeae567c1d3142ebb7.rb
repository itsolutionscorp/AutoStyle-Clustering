class Array
	def accumulate
		self.map {|i|
			yield i
		}
	end
end
