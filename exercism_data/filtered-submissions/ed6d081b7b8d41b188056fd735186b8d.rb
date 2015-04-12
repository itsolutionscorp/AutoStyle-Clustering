class Hamming

	def compute( baseGenome, mutateGenome)
	  @counter = 0

	  for index in (0...baseGenome.length)
	  	 if baseGenome.slice(index) != mutateGenome.slice(index); @counter = @counter + 1 ; end

	  end

	  return @counter

	end

end
