class House
  @@song = 
    "This is the horse and the hound and the horn
that belonged to the farmer sowing his corn
that kept the rooster that crowed in the morn
that woke the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog
that worried the cat
that killed the rat
that ate the malt
that lay in the house that Jack built".split("\n")

  def self.recite
    res = ""

    0.upto(@@song.size - 1) do |n|
      start = @@song.size - 1 - n
      lim = @@song.size - 1

      start.upto(lim) do |i|
        line = @@song[i]
        if i == start
          parts = line.partition("the")
          res << "This is #{parts[1]}#{parts[2]}"
        else
          res << line
        end
        res << "." if i == lim
        res << "\n"
      end

      res << "\n"
    end

    res.chomp
  end
end
