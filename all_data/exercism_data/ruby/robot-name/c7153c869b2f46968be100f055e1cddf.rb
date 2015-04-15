class Robot
	def name	
		if @hello == nil
		@hello =  []
		@hello.push(2.times.map{ ('A'..'Z').to_a[rand(26)]})
		@hello.push(3.times.map{Random.rand(10)})
		return @hello.join
		end
	end
	def reset
		@hello = nil
	end

end
