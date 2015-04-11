class House 
  S1 = "the house that Jack built."
  SUBJ = ["malt", "rat", "cat", "dog", "cow with the crumpled horn", "maiden all forlorn", "man all tattered and torn", "priest all shaven and shorn", "rooster that crowed in the morn", "farmer sowing his corn", "horse and the hound and the horn"]
  VERB = ["lay in #{S1}", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"]

  def self.recite
    SUBJ.each_with_index.inject("") do |song, (subj,i)|
      song += "This is #{S1}\n\n" if i == 0
      song + verse(i) + "\n\n"
    end.chomp
  end

  def self.verse(index)
    index.downto(0).with_object(String.new) do |i, result|
      if (index-i).eql?(0)
        result << "This is the #{SUBJ[index]}\nthat #{VERB[index]}"
      else
        result << " the #{SUBJ[i]}\nthat #{VERB[i]}"
      end
    end
  end
end
