class Ham

  # def initialize(firststrand, secondstrand)
  # 	@firststrand = firststrand
  # 	@secondstrand = secondstrand
  # 	@hdistance = 0
  # end

def compute(firstrand, secondstrand)
	@firstrand = firstrand
	@secondstrand = secondstrand
	firstSL = @firstrand.length
	secondSL = @secondstrand.length
	if firstSL > secondSL
		@strandlength = secondSL
	else secondSL > firstSL
		@strandlength = firstSL
	end

	@hdistance = 0
	@@i = 0
	while @@i < @strandlength
		if @firstrand[@@i] != @secondstrand[@@i]
			@hdistance += 1
		end
		@@i += 1
	end
	return @hdistance
end
end

Hamming = Ham.new
