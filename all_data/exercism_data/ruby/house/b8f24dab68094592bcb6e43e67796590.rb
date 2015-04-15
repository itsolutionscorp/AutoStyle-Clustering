class House
  SubjectActionVerseComponent = Struct.new(:subject, :action) do
    def to_s
      "#{subject} that #{action} the"
    end
  end

  SUBJECT_ACTION_COMPONENTS = [
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
  ].map { |subject, action| SubjectActionVerseComponent.new(subject, action) }

  VERSE_COMPONENTS = ["house"] + SUBJECT_ACTION_COMPONENTS

  def verse(verse_number)
    verse_body = VERSE_COMPONENTS.take(verse_number).reverse.join(" ")
    "This is the #{verse_body} that Jack built.\n"
  end

  def verses(from_verse, to_verse)
    (from_verse..to_verse).map { |verse_number| "#{verse(verse_number)}\n" }.join
  end
end
