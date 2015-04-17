class House
  ORDERED_CHARACTER_ACTIONS_LIST = [{:subject=>:malt, :verb=>:lay}, {:subject=>:rat, :verb=>:ate},
                            {:subject=>:cat, :verb=>:killed}, {:subject=>:dog, :verb=>:worried},
                            {:subject=>:cow, :adjective=>:"with the crumpled horn", :verb=>:tossed},
                            {:subject=>:maiden, :adjective=>:"all forlorn", :verb=>:milked},
                            {:subject=>:man, :adjective=>:"all tattered and torn", :verb=>:kissed},
                            {:subject=>:priest, :adjective=>:"all shaven and shorn", :verb=>:married},
                            {:subject=>:rooster, :adjective=>:"that crowed in the morn", :verb=>:woke},
                            {:subject=>:farmer, :adjective=>:"sowing his corn", :verb=>:kept},
                            {:subject=>[:horse, :hound, :horn], :verb=>"belonged to"}]

  def make_phrase(data)
    subject = data[:subject]
    subject = subject.join(" and the ") if subject.is_a?(Array)
    [:the, subject, data[:adjective], :that, data[:verb]].compact.join(" ")
  end

  def make_rhyme(num)
    rhyme_skeleton = ["This is","the house that Jack built.\n"]
    if(num > 1)
      rhyme_phrases = ORDERED_CHARACTER_ACTIONS_LIST[0, num - 1].reverse.map{|data| make_phrase(data)}.join(" ")
      rhyme_skeleton.insert(1, rhyme_phrases, :in)
    end
    rhyme_skeleton.join(" ")
  end

  def verse(num)
    make_rhyme(num)
  end

  def verses(start_num, end_num)
    (start_num..end_num).map{|n| verse(n)}.zip(["\n"]*(end_num - start_num + 1)).join
  end
end