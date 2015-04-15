class House

  def initialize
    reset_rhyme
  end

  def verse(line_number)
    if line_number == 0
      @rhyme += ".\n"
      return @rhyme
    end
    @rhyme += " the #{rhymes[line_number-1].first} that #{rhymes[line_number-1].last}"
    verse(line_number-1)
  end

  def verses(start_line, end_line)
    start_line.upto(end_line).inject("") do |memo, line_number|
      reset_rhyme
      memo += "#{verse(line_number)}\n"
    end
  end

  private

  def reset_rhyme
    @rhyme = "This is"
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
