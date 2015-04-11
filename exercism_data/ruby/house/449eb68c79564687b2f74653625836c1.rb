class House
  def self.recite
    verb_and_nouns.map.with_index do |(verb, noun), index|
      first_line = "This is the #{noun}"
      other_lines = verb_and_nouns[0...index].reverse.map do |prev_verb, prev_noun|
        "\nthat #{prev_verb} the #{prev_noun}"
      end.join

      first_line + other_lines
    end.join("\n\n") + "\n"
  end

  def self.verb_and_nouns
    [
      ["lay in", "house that Jack built."],
      ["ate", "malt"],
      ["killed", "rat"],
      ["worried", "cat"],
      ["tossed", "dog"],
      ["milked", "cow with the crumpled horn"],
      ["kissed", "maiden all forlorn"],
      ["married", "man all tattered and torn"],
      ["woke", "priest all shaven and shorn"],
      ["kept", "rooster that crowed in the morn"],
      ["belonged to", "farmer sowing his corn"],
      ["", "horse and the hound and the horn"]
    ]
  end
end
