class Proverb
 # "For want of a shoe the horse was lost.\n" +
 # "And all for the want of a nail."
	def initialize(*chain, qualifier: {})
		@consequences = chain
		@qualifier = qualifier[qualifier] # + " " if !qualifier[:qualifier].nil?
	end

	def to_s


    proverb = construct_consequences
    
    @qualifier += " " if !@qualifier.nil?
    proverb += "And all for the want of a #{@qualifier}#{@consequences[0]}."
	end

	def construct_consequences
		proverb = ""
		1.upto(@consequences.length-1) do |i|
				proverb += "For want of a #{@consequences[i-1]}" + " the #{@consequences[i]} was lost.\n" 
		end
		proverb
	end

end
