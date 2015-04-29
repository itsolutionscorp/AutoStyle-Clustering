class House
  LINES = [
      [nil, 'the horse and the hound and the horn'],
      ['belonged to', 'the farmer sowing his corn'],
      ['kept', 'the rooster that crowed in the morn'],
      ['woke', 'the priest all shaven and shorn'],
      ['married', 'the man all tattered and torn'],
      ['kissed', 'the maiden all forlorn'],
      ['milked', 'the cow with the crumpled horn'],
      ['tossed', 'the dog'],
      ['worried', 'the cat'],
      ['killed', 'the rat'],
      ['ate', 'the malt'],
      ['lay in', 'the house that Jack built'],
  ]

  def self.recite
    1.upto(LINES.size).map do |part_number|
      recite_part(part_number)
    end.join("\n\n") + "\n"
  end

  private

  def self.recite_part(number)
    first_line, *rest = LINES.last(number)
    if number == 1
      recite_first_line(first_line)
    else
      recite_first_line(first_line) + "\n" + recite_rest(rest)
    end + '.'
  end

  def self.recite_first_line(line)
    "This is #{line.last}"
  end

  def self.recite_rest(lines)
    lines.map do |line|
      "that #{line.first} #{line.last}"
    end.join("\n")
  end
end
