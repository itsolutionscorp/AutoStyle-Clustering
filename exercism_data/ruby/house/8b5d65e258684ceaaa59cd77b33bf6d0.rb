class House
  ORDERED_CHARACTER_ACTIONS_LIST = [{:subject=>:malt, :verb=>:lay}, {:subject=>:rat, :verb=>:ate},
                            {:subject=>:cat, :verb=>:killed}, {:subject=>:dog, :verb=>:worried},
                            {:subject=>:cow, :adjective=>:"with the crumpled horn", :verb=>:tossed},
                            {:subject=>:maiden, :adjective=>:"all forlorn", :verb=>:milked},
                            {:subject=>:man, :adjective=>:"all tattered and torn", :verb=>:kissed},
                            {:subject=>:priest, :adjective=>:"all shaven and shorn", :verb=>:married},
                            {:subject=>:rooster, :adjective=>:"that crowed in the morn", :verb=>:woke},
                            {:subject=>:farmer, :adjective=>:"sowing his corn", :verb=>:kept},
                            {:subject=>[:horse, :hound, :horn], :verb=>:"belonged to"}]

  def phrase(data)
    subject = data[:subject]
    subject = subject.join(" and the ") if subject.is_a?(Array)
    [:the, subject, data[:adjective], :that, data[:verb]].compact.join(" ")
  end

  def verse(num)
    rhyme_skeleton = ["This is","the house that Jack built.\n"]
    if(num > 1)
      rhyme_phrases = ORDERED_CHARACTER_ACTIONS_LIST[0, num - 1].reverse.map{|data| make_phrase(data)}.join(" ")
      rhyme_skeleton.insert(1, rhyme_phrases, :in)
    end
    rhyme_skeleton.join(" ")
  end

  def verses(start_verse_number, end_verse_number)
    newlines = ["\n"]*(end_verse_number - start_verse_number + 1)
    (start_verse_number..end_verse_number).map{|verse_number| verse(verse_number)}.zip(newlines).join
  end
end
