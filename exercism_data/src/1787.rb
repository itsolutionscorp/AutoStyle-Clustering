class Hamming

 	def compute(letter,letter2)
 		count =0
  		(0..letter.length).each{|i| count +=1 if !letter[i].eql?(letter2[i])}
 		return count
 	end
 end
