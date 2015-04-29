require 'net/http'
require 'json'

# I wanted to localize Bob, so I made these files. Normally I would just include them
# with bob.rb, but I belatedly realized I can only upload one file to exercism. So I 
# uploaded them elsewhere. If you download them, you can uncomment the alternative 
# 'initialize' code to see the version that uses the config file. Ok yes, it is 
# over-engineered Bob.
# http://www.robertbeverly.com/files/7513/8040/1125/config.json
# http://www.robertbeverly.com/files/1913/8040/1126/responses.json
# http://www.robertbeverly.com/files/2213/8040/1126/responses-leet.json

class Bob
	
	# load localization/response file
	def initialize
# 		config_path = File.expand_path("../config.json", __FILE__)
# 		response_path = File.expand_path("../" + JSON.parse( IO.read(config_path) )["localization file"], __FILE__)
# 		@responses = JSON.parse( IO.read(response_path) )
		uri = URI('http://www.robertbeverly.com/files/1913/8040/1126/responses.json')
		@responses = JSON.parse( Net::HTTP.get(uri) )
	end
	
	# respond lackadaisically... his mom says it's just a phase
	def hey(arg)
		case
			when is_question(arg)     then @responses["question"]
			when is_shout(arg)        then @responses["shout"]
			when is_whitespace(arg)   then @responses["whitespace"]
			else                           @responses["other"]
		end
	end

	# POSIX character classes support non-English alphabets, whereas [a-z] would not.
	# end_with?("?") probably fails in some languages, but I stopped short of learning
	# multi-lingual punctuation.
	def is_question(arg)
		arg.end_with?("?") and arg.match(/\p{Lower}/) || !arg.match(/\p{Upper}/)
	end
	
	def is_shout(arg)
		!arg.match(/\p{Lower}/) && arg.match(/\p{Upper}/)
	end
	
	def is_whitespace(arg)
		!arg.match(/\p{Graph}/)
	end
	
end
