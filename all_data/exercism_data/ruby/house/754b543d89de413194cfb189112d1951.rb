#-This is the horse and the hound and the horn
#-that belonged to the farmer sowing his corn
#-that kept the rooster that crowed in the morn
#-that woke the priest all shaven and shorn
#-that married the man all tattered and torn
#-that kissed the maiden all forlorn
#-that milked the cow with the crumpled horn
#-that tossed the dog
#-that worried the cat
#-that killed the rat
#-that ate the malt
#-that lay in the house that Jack built.

class House
  def self.recite
    lines = ["",
             "malt\nthat lay in the ",
             "rat\nthat ate the ",
             "cat\nthat killed the ",
             "dog\nthat worried the ",
             "cow with the crumpled horn\nthat tossed the ",
             "maiden all forlorn\nthat milked the ",
             "man all tattered and torn\nthat kissed the ",
             "priest all shaven and shorn\nthat married the ",
             "rooster that crowed in the morn\nthat woke the ",
             "farmer sowing his corn\nthat kept the ",
             "horse and the hound and the horn\nthat belonged to the "
            ]

    result, lines_to_add = "", ""
    lines.each do |line|
      lines_to_add.prepend line
      result << "This is the #{lines_to_add}house that Jack built.\n"
      result << "\n" unless line == lines.last
    end
    
    result
  end
end

House.recite
