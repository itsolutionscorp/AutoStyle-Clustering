class House
  RHYME_PAIRS = [
      ["house that Jack built.", "\n"],
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
      ["horse and the hound and the horn", "belonged to"]
  ]

  def self.recite
    poem = ""
    0.upto(RHYME_PAIRS.length-1) do |iteration|
      poem += "This is " + build_stanza(iteration)
    end
    poem.chomp      #remove the trailing space from last line of last stanza
                    #considered just changing the test to add the blank at the end <g>
  end

  private

  def self.build_stanza(iteration)
    return "the " + RHYME_PAIRS[iteration][0]+ "\n" + RHYME_PAIRS[iteration][1] if iteration == 0
    return "the " + RHYME_PAIRS[iteration][0]+ "\nthat " + RHYME_PAIRS[iteration][1] + " " + build_stanza(iteration-1)

  end


end
