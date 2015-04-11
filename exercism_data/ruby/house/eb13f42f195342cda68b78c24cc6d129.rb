class House
  def initialize
    @noun = ["house", "malt", "rat", "cat", "dog", "cow with the crumpled horn", "maiden all forlorn", "man all tattered and torn", "priest all shaven and shorn", "rooster that crowed in the morn", "farmer sowing his corn", "horse and the hound and the horn"]
    @verb = ["Jack built", "lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"]
  end

  def verse(verse_num, string="")
    return string if verse_num == 0
    string = "This is the" if string.empty?
    string << " #{@noun[verse_num-1]} that #{@verb[verse_num-1]}"
    string << case verse_num
                when 1
                  ".\n"
                else
                  " the"
              end
    verse(verse_num-1, string)
  end

  def verses(min, max)
    (min..max).each_with_object("") { |i, a| a << verse(i) + "\n" }
  end
end
