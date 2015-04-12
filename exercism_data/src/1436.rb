class Hamming
	def compute(typeA, typeB)
		it=0
		dif=0
		while(it < typeA.bytesize) do
			dif+=1 if typeA[it]!=typeB[it]
			it+=1
		end
		dif
	end
end
