#!/usr/bin/ruby

# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  ay=Array.new
  words.each do |x|
	tmp_x = x.downcase.scan(/./).sort
	#puts x.inspect
	#puts tmp_x.inspect
	found = false
	ay.each do |y|
		#puts "each" + y.inspect
		#puts y[0].inspect
		if y[0].downcase.scan(/./).sort == tmp_x then
			y<<x
			#puts "if"+y.inspect
			found = true
		end
	end
	if found == false then
		y=Array.new(1,x)
		ay<<y
		#puts "false"+ay.inspect
	end
  end
  return ay
end

#inputs=['cArs', 'forr', 'potatoes', 'raCs', 'four','scar', 'crEams', 'scream']
#puts combine_anagrams(inputs).inspect
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
