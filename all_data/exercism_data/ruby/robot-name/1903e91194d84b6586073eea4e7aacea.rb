class Robot
	@@list = []
	CHR = (0..9).to_a.map {|c| c.to_s }
	LET = ("A".."Z").to_a + ("a".."z").to_a
	
	def initialize
		generate
		while @@list.include?(@name)
			generate
		end
		@@list.push(@name)
	end

	def name
		@name
	end

	def reset
		temp = @name
		while @@list.include?(@name)
			generate
		end
		@@list.delete(temp)
		@@list.push(@name)
	end

	def generate
		@name = ""
		2.times  {@name << LET[Random.new.rand(0..51)]}
		3.times  {@name << CHR[Random.new.rand(0..9)]}
	end
end
