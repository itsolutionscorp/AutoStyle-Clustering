class BeerSong
  
  def verse(number)
    first_line(number) + 
    second_line(number)
  end
  
  def verses(starting_verse, finishing_verse)
    starting_verse
      .downto(finishing_verse)
      .collect { |verse_number| verse(verse_number) }
      .join("\n").concat("\n")
  end
  
  def sing 
    verses(99, 0)
  end
    
    
  private
  
  def first_line number
    "#{titleize(quantity_phrase_for(number))} #{pluralised_form_of_bottle_for(number)} of beer on the wall, #{quantity_phrase_for(number)} #{pluralised_form_of_bottle_for(number)} of beer.\n"
  end
  
  def second_line number
    case number
    when 0
      "Go to the store and buy some more, #{quantity_phrase_for(99)} #{pluralised_form_of_bottle_for(99)} of beer on the wall.\n"
    else
      "Take #{pronoun_for(number)} down and pass it around, #{quantity_phrase_for(number - 1)} #{pluralised_form_of_bottle_for(number - 1)} of beer on the wall.\n"
    end
  end
  
  def titleize string
    string = String(string)
    string.slice(0,1).capitalize + string.slice(1..-1)
  end
  
  def pronoun_for(number)
    case number
    when 1
      "it"
    else 
      "one"
    end
  end
  
  def quantity_phrase_for(number)
    case number 
    when 0
      "no more"
    else
      number
    end
  end
    
  def pluralised_form_of_bottle_for(number)
    case number
    when 1 
      "bottle"
    else 
      "bottles"
    end
  end
    
end
