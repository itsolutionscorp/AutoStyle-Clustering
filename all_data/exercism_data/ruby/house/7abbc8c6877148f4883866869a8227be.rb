class House

  VERSES = {
    1 => { action: "Jack built", receiver: "house" },
    2 => { action: "lay in", receiver: "malt" },
    3 => { action: "ate", receiver: "rat" },
    4 => { action: "killed", receiver: "cat" },
    5 => { action: "worried", receiver: "dog" },
    6 => { action: "tossed", receiver: "cow with the crumpled horn" },
    7 => { action: "milked", receiver: "maiden all forlorn" },
    8 => { action: "kissed", receiver: "man all tattered and torn" },
    9 => { action: "married", receiver: "priest all shaven and shorn" },
    10 => { action: "woke", receiver: "rooster that crowed in the morn" },
    11 => { action: "kept", receiver: "farmer sowing his corn" },
    12 => { action: "belonged to", receiver: "horse and the hound and the horn" },
  }

  def initialize
    @rhyme = []
  end

  def verse(num)
    subject_receiver = num.downto(1).map do |verse|
      verses = VERSES[verse]
      "#{verses[:receiver]} that #{verses[:action]}"
    end
    "This is the #{subject_receiver.join(' the ')}.\n"
  end

  def verses(start, finish)
    (start.upto(finish).map { |line| verse(line) }).join("\n") + "\n"
  end

end
