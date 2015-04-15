def compute(cad1, cad2)
		dif=0
	#	if cad1.length > cad2.length
	#		n=cad2.length
	#	else
	#		n=cad1.length
	#	end if
		for i in 0..cad1.length-1
			if cad2[i] 
				if cad1[i] != cad2[i]
					dif = dif +1
				end
			end
		end
		dif
		
	end