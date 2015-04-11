class Proverb
=begin  
For want of a nail the shoe was lost.
For want of a shoe the horse was lost.
For want of a horse the rider was lost.
For want of a rider the message was lost.
For want of a message the battle was lost.
For want of a battle the kingdom was lost.
And all for the want of a horseshoe nail.
=end

	def initialize(*chain, qualifier: {})
		@consequences = chain
		@qualifier = qualifier[qualifier] 
	end

	def to_s
		all_phrases.join("\n")
	end

  private
  
	def all_phrases
		chain_of_consequences << end_phrase
		#NOTE: chain_of_consequences is an array,
		#so here, the string called end_phrase gets pushed onto the array
		#producing an array of strings
	end

	def chain_of_consequences
		@consequences.each_cons(2).map do |consequenc1, consequenc2|
			"For want of a #{consequenc1} the #{consequenc2} was lost." 
		end
	end

	def end_phrase
    "And all for the want of a #{qualifier_consequence}." 
  end

  def qualifier_consequence
  	[@qualifier,@consequences.first].compact.join(" ")
  end
	
end
