class House

  def verse(line_number)
    "This is #{generate_verse(line_number-1)}.\n"
  end

  def verses(start_line, end_line)
    start_line.upto(end_line).inject("") do |memo, line_number|
      memo += "#{verse(line_number)}\n"
    end
  end

  private

  def generate_verse(line_number)
    rhyme = "the #{rhymes[line_number].first} that #{rhymes[line_number].last}"
    if line_number == 0
      rhyme
    else
      rhyme += " #{generate_verse(line_number-1)}"
    end
  end

  def rhymes
    [
      ['house', 'Jack built'],
      ['malt', 'lay in'],
      ['rat', 'ate'],
      ['cat', 'killed'],
      ['dog', 'worried'],
      ['cow with the crumpled horn', 'tossed'],
      ['maiden all forlorn', 'milked'],
      ['man all tattered and torn', 'kissed'],
      ['priest all shaven and shorn', 'married'],
      ['rooster that crowed in the morn', 'woke'],
      ['farmer sowing his corn', 'kept'],
      ['horse and the hound and the horn', 'belonged to']
    ]
  end
end
