# recursive

 class Grains

	def square(space)
		space_counter = 1 
		rice = 1
		while space_counter < space
			rice = square(space_counter)
			space_counter += 1
			rice *= 2			
		end 
		return rice
	end

	def total
       rice = 1
       space = 1
       while space <= 64
       	rice *= 2
       	space +=1
       end
       return rice -1 
	end
end


# iterative

class Grains

  def square(space)
       rice = 1
       space_counter = 1
       while space_counter <= space
       	rice *= 2
       	space_counter +=1
       end
       return rice - rice/2  
  end

    def total
       rice = 1
       space = 1
       while space <= 64
       	rice *= 2
       	space +=1
       end
       return rice -1 
	end
end
