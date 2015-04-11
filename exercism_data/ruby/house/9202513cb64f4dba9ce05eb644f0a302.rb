class House

  DATA = [
    ['lay in', 'house that Jack built.'],
    ['ate', 'malt'],
    ['killed', 'rat'],
    ['worried', 'cat'],
    ['tossed', 'dog'],
    ['milked', 'cow with the crumpled horn'],
    ['kissed', 'maiden all forlorn'],
    ['married', 'man all tattered and torn'],
    ['woke', 'priest all shaven and shorn'],
    ['kept', 'rooster that crowed in the morn'],
    ['belonged to', 'farmer sowing his corn'],
    ['', 'horse and the hound and the horn']
  ]

  class << self
    def recite
      12.times.map do |i|
        verse(i)
      end.join("\n")
    end

    def verse nb
      present(nb) + [*0..(nb-1)].reverse.map(&method(:story)).join
    end

    private

    def present nb
      "This is the #{character(nb)}\n"
    end

    def story nb
      "that #{action(nb)} the #{character(nb)}\n"
    end

    def action nb
      DATA[nb].first
    end

    def character nb
      DATA[nb].last
    end
  end

end
