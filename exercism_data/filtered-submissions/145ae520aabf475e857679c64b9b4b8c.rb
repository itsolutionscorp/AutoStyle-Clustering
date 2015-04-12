def compute(herp, derp)
  	herpArray = herp.split(//)
  	derpArray = derp.split(//)
  	# puts ""
  	# puts "herpArray is #{herpArray}"
  	# puts "derp is #{derp}"
  	@numberOfMatches = 0
  	iterator = 0
  	while iterator < herpArray.length
  		if herpArray[iterator] != derpArray[iterator]
  			# puts "#{herpArray[iterator]} is equal to #{derpArray[iterator]}"
  			@numberOfMatches += 1
  		end
  		iterator += 1
  	end

  	return @numberOfMatches
  end

  # puts self.compute('A', 'A')