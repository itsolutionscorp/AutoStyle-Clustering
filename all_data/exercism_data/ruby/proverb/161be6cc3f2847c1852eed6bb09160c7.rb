class Proverb < String

	def initialize(*objects, qualifier: nil)
	  string = String.new	

		objects[0..-2].each_with_index do |word, i|
			string << 
			    "For want of a #{word} the #{objects[i + 1]} was lost.\n"
		end

		super(string << 
					"And all for the want of a " <<  
					"#{qualifier + ' ' if qualifier}#{objects.first}.")
	end
end
