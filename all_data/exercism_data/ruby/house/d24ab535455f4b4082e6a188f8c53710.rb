class House
  SUBJECTS = ["house that Jack built", "malt", "rat", "cat", "dog",
              "cow with the crumpled horn", "maiden all forlorn",
              "man all tattered and torn", "priest all shaven and shorn",
              "rooster that crowed in the morn", "farmer sowing his corn",
              "horse and the hound and the horn"]

  VERBS = ["lay in", "ate", "killed", "worried", "tossed", "milked", "kissed",
           "married", "woke", "kept", "belonged to"]

  def self.recite
    new.recite
  end

  def recite
    verses.join("\n\n").concat("\n")
  end

  private

  def verses
    SUBJECTS.size.times.map { |n| verse(n) }
  end

  def verse(n)
    lines_for_verse(n).join("\n").concat(".")
  end

  def lines_for_verse(n)
    n.downto(0).map do |line|
      verb = line == n ? "This is" : "that #{VERBS[line]}"
      subject = SUBJECTS[line]

      "#{verb} the #{subject}"
    end
  end
end
