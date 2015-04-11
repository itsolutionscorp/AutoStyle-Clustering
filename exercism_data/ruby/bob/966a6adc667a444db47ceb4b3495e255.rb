class Bob
	def responsePatterns
		[{:pattern => /^\s*$/,     :response => "Fine. Be that way!"},
		 {:pattern => /^[^a-z]+$/, :response => "Woah, chill out!"  },
		 {:pattern => /\?$/,       :response => "Sure."             },
		 {:pattern => /.*/,        :response => "Whatever."         }]
	end

	def hey (whatBobHeard)
		whatBobHeard = whatBobHeard.gsub(/\n|\r/, "")
		patterns = self.responsePatterns
		until (result = patterns.shift)[:pattern] =~ whatBobHeard do end
		result[:response]
	end
end
