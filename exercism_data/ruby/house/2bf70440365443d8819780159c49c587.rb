class House

  def initialize
    @nouns = ["house", "malt", "rat", "cat", "dog", "cow with the crumpled horn", "maiden all forlorn", "man all tattered and torn", "priest all shaven and shorn", "rooster that crowed in the morn", "farmer sowing his corn", "horse and the hound and the horn"]
    @verbs = ["Jack built", "lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"]
  end

  def verse(num, str="")
    return str if num == 0 
    str = "This is the " if str.empty?
    str << "#{@nouns[num-1]} that #{@verbs[num-1]}"
    str << case num
      when 1
        ".\n"
      else
        " the "
      end 
    verse(num-1, str)
  end

  def verses(min, max) 
    (min..max).each_with_object(""){ |i, song| song << verse(i) + "\n"}
  end
end
