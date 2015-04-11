class House
  def verse(number)
    "This is " + message(number - 1) + "\n"
  end

  def verses(start, stop)
    (start..stop).each_with_object("") do |i, output|
      output << verse(i) + "\n"
    end
  end

  private

  def message(number)
    rhyme = ["the", find_creature(number), "that", find_action(number)]
    rhyme << message(number - 1) if number > 1
    rhyme.join(" ")
  end

  def creature_actions
    [ { "house" => "Jack built." },
      { "malt" => "lay in the house that Jack built." },
      { "rat" => "ate" },
      { "cat" => "killed" },
      { "dog" => "worried" },
      { "cow with the crumpled horn" => "tossed" },
      { "maiden all forlorn" => "milked" },
      { "man all tattered and torn" => "kissed" },
      { "priest all shaven and shorn" => "married" },
      { "rooster that crowed in the morn" => "woke" },
      { "farmer sowing his corn" => "kept" },
      { "horse and the hound and the horn" => "belonged to" } ]
  end

  def find_creature(i)
    creature_actions[i].keys.first
  end

  def find_action(i)
    creature_actions[i].values.first
  end

end
