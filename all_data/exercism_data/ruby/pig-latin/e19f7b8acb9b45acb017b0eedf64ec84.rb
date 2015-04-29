class PigLatin

	@@CONVERSIONS = {
		/^ch(.*)/    => "$1chay",
		/^(.)qu(.*)/ => "$2$1quay",
		/^qu(.*)/    => "$1quay",
		/^thr(.*)/   => "$1thray",
		/^th(.*)/    => "$1thay",
		/^sch(.*)/   => "$1schay",
		/^y(e.*)/    => "$1yay",
		/^(yt.*)/    => "$1ay",
		/^x(e.*)/    => "$1xay",
		/^(xr.*)/    => "$1ay",
		/^([aeiou].*)/  => "$1ay", 
		/^([bcdfghjklmnpqrstvwxyz])(.*)/  => "$2$1ay", 
	}

	@@CONVERSIONS.default_proc = lambda do |hash, lookup|
		hash.each_pair do |key, value|
			if key =~ lookup
				return value.gsub(/\$\d/, '$1' => $1, '$2' => $2)
			end
		end
		return nil
	end

	def self.translate(phrase)
		phrase.split.map { |word| @@CONVERSIONS[word] || word }.join(" ")
	end

	
end
