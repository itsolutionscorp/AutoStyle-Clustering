class FoodChainSong
    def verse(sentences)
        song = File.read("README.md").split("plain")[1].split("\n")
        song.shift
        ret = ""
        (0..sentences).each do |i|
            ret << "#{song[i]}\n"
        end
       ret
    end
end
