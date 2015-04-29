def compute(first, second)

      	String.try_convert(first)
      	String.try_convert(second)

      	count=0

      	first.each_char do |i|
      		if i!=second[0]
      			count=count+1
      		end
      		second = second[1..-1]
      	end

      	return count





      end