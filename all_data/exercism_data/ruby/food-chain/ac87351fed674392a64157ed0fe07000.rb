class FoodChainSong
    def initialize
        flag = false # FLAG TO TELL WHEN SONG STARTS/ENDS IN README.md
        @song = [] # HOLDS ENTIRE SONG
        @v = [0] # HOLDS START POSITION OF EACH VERSE
        IO.foreach("D:/Users/Jimmy/exercism/ruby/food-chain/README.md") do |line|
            if flag && line[0,1]!="`" && line[0,2]!="\n"
                @song.push(line)
                @v.push(@song.length) if line.include? "Perhaps she'll die.\n"
            end
            flag = !flag if line[0,1]=="`" # INVERT FLAG WHEN WE SEE ` AT START OF LINE; THIS CHAR DELIMITS SONG IN FILE
        end
        @v.push(@song.length) # NEED TO KNOW WHERE SONG ENDS
    end
    def verse(i)
        @song[@v[i-1]..@v[i]-1].join
    end
    def verses(i,j)
        (i..j).each.map { |k| verse(k) + "\n" }.join
    end
    def sing
        verses(1, @v.length-1)
    end
end
