class Bob
	def responsePatterns
		[{:pattern => /^\s*$/,     :response => "Fine. Be that way!"},
		 {:pattern => /^[^a-z]+$/, :response => "Woah, chill out!"  },
		 {:pattern => /\?$/,       :response => "Sure."             },
		 {:pattern => /.*/,        :response => "Whatever."         }]
	end

	def hey (whatBobHeard)
		whatBobHeardInOneLine = whatBobHeard.gsub(/\n|\r/, "")
		result = self.responsePatterns.find do |patternResponse|
			patternResponse[:pattern] =~ whatBobHeardInOneLine
		end
		result[:response]
	end
end
