class House
  def self.recite
    raw = [
      nil,
      ["malt", "lay in"],
      ["rat", "ate"],
      ["cat", "killed"],
      ["dog", "worried"],
      ["cow with the crumpled horn", "tossed"],
      ["maiden all forlorn", "milked"],
      ["man all tattered and torn", "kissed"],
      ["priest all shaven and shorn", "married"],
      ["rooster that crowed in the morn", "woke"],
      ["farmer sowing his corn", "kept"],
      ["horse and the hound and the horn", "belonged to"],
    ].reverse
    raw.size.times.collect do |range|
      raw.last(range + 1).each_with_index.inject("This is ") do |rhyme, (data, i)|
        if data.nil?
          rhyme
        else
          rhyme += "the #{data[0]}\nthat #{data[1]} "
        end
      end << "the house that Jack built.\n"
    end.join "\n"
  end
end
