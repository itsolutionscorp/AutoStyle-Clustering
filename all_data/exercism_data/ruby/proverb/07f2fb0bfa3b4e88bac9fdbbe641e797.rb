class Proverb < String
	def initialize(*args)
		items, options = parse(args)
		
		super(items.each_cons(2).inject("") do |text, pair|
			 text + verse(*pair)
		end + ending(options[:qualifier], items.first))
	end
	
private
	def parse(args)
		if args.last.is_a?(Hash)
			[args[0..-2], args.last]
		else
			[args, {}]
		end
	end
	
	def verse(lack, lost)
		"For want of a #{lack} the #{lost} was lost.\n"
	end
	
	def ending(qualifier, thing)
		what = qualifier.nil? ? thing : "#{qualifier} #{thing}"
		"And all for the want of a #{what}."
	end

end
