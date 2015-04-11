class House
  def self.recite
    rhyme = [['lay in', 'house that Jack built.'],
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
             ['', 'horse and the hound and the horn']]
    full_rhyme = ""
    x = 0

    while x < rhyme.length
      full_rhyme << "This is the #{rhyme[x][1]}\n"
      y = x - 1
      while y >= 0
        full_rhyme << "that #{rhyme[y][0]} the #{rhyme[y][1]}\n"
        y -= 1
      end
      x += 1
      full_rhyme << "\n" if x != rhyme.length
    end
    puts full_rhyme
    full_rhyme
  end
end
