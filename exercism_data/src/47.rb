def compute(herp, derp)
  	herpArray = herp.split(//)
  	derpArray = derp.split(//)
  	puts ""
  	puts "herpArray is #{herpArray}"
  	puts "derp is #{derp}"
  	@numberOfMatches = 0
  	# herpArray.each  {|val, index | puts "#{val} => #{index}" }
  	# 	puts "#{val} => #{index}"
  	# 	if herpArray[index].to_s.eql? derpArray[index].to_s
  	# 		puts "#{herpArray[index]} is equal to #{derpArray[index]}"
  	# 	else
  	# 		puts "Incrementing the number of matches because #{h} matches"
  	# 		@numberOfMatches += 1
  	# 	end
  	end

  	return @numberOfMatches
  end

  puts self.compute('AGA', 'AGG')