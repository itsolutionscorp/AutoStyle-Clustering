class FoodChainSong
	attr_accessor :lyrics

	def initialize
		@lyrics = File.read("README.md").split("plain\n")[1].split("\n```")[0]
	end

	def verse (num)
		split[num-1] + "\n"
	end

	def verses (num1 , num2)
		verse = ""
		(num1..num2).each do |x|
			verse << split[x-1]
			verse << "\n\n" if x!=8		
		end
		verse
	end

	def sing
		@lyrics
	end

	private

		def split
			lyrics.split("\n\n")
		end
		
end
