class Complement
	def self.of_dna(ch)

		myhash = Hash["G","C","C","G","T","A","A","U"]
		s = ""
		if ch.length > 1
			ch.split("").each { |e|  s<<myhash[e] }
			return s
		end

		return myhash[ch]
	end

	def self.of_rna(ch)
		myhash = Hash["C","G","G","C","A","T","U","A"]
		s =""
		if ch.length > 1
			ch.split("").each { |e|  s<<myhash[e] }
			return s
		end
		return myhash[ch]
	end
end
