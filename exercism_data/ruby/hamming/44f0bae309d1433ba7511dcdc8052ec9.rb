module Hamming
	def Hamming.compute(norm, mut)
		dist = 0
		if norm.length > mut.length then shorter = mut else shorter = norm end
		if norm.length <= mut.length then  longer = mut else longer = norm end
		shorter.split("").each_with_index do |n,i|
			if n!= longer[i]
				dist+=1
			end
		end
		return dist
	end
end
