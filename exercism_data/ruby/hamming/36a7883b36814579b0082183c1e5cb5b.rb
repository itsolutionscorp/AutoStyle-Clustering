#Created by Kallistia Giermek 

puts "what is your first DNA strand? Type all leters together ex: CGCATA"
DNA1 = gets.scan /\w/ #Converts string to array

puts "what is your second DNA strand? Type all leters together ex: CGCATA"
DNA2 = gets.scan /\w/

hamming = 0
loop_counter = 0 

while loop_counter < DNA1.length && loop_counter < DNA2.length do 
		if DNA1[loop_counter].to_s != DNA2[loop_counter].to_s
		hamming = hamming + 1 
	else
		hamming = hamming
	end
	loop_counter += 1
end

puts hamming
