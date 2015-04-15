def compute(herp, derp)
  	herpArray = herp.split(//)
  	derpArray = derp.split(//)



  	@numberOfMatches = 0
  	iterator = 0
  	while iterator < herpArray.length
  		if herpArray[iterator] != derpArray[iterator]

  			@numberOfMatches += 1
  		end
  		iterator += 1
  	end

  	return @numberOfMatches
  end