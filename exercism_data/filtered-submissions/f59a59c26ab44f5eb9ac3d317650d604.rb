def compute(first_strand, second_strand)
  	index = 0
  	count = 0
  	length = [first_strand.length, second_strand.length].min
  	first_strand.each_char do |char|
  	  break if index >= length
  	  if second_strand[index] != char
  	  	count += 1
  	  end
  	  index += 1
  	end
    count
  end