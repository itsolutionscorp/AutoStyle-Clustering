class BeerSong
	attr_reader :lyrics

    def initialize
			@lyrics = File.read("README.md").split("plain\n")[1].split("\n```")[0]
    end

    def verse (num)
    	lyrics.split("\n\n")[99-num] + "\n"
    end

    def verses (endVerse , startVerse)
    	(endVerse.downto startVerse).each_with_object("") do |x, verses|
    		x != 0 ? verses << verse(x) + "\n" : verses << verse(x).strip
    	end
    end

    def sing
			@lyrics
    end

end
