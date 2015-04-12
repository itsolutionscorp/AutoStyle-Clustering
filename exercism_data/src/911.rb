def compute(first, second)
  	counter = 0
  	second.chars.each_with_index do |letter, index|
  		counter += 1 if first.chars[index] != letter
  	end
  	counter
  end