class Atbash
	CODE =Hash[("a".."z").to_a.zip ("a".."z").to_a.reverse]
	def self.encode(string)
		array = string.downcase.split(//).map do |a|
			if a.match(/[1-9]/)
				a
			elsif a.match(/[a-z]/)
				CODE[a]
			end
		end
		array.join("").gsub(/(.{5})/, '\1 ').strip
	end
end
