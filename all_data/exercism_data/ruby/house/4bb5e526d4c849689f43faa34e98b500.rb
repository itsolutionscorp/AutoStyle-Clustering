 # PLEASE DISCARD THIS
 # THIS IS NOT FULLY DONE


require 'pry'
# class House
class House
  FULL_POEM = [
    'This is the horse and the hound and the horn',
    'that belonged to the farmer sowing his corn',
    'that kept the rooster that crowed in the morn',
    'that woke the priest all shaven and shorn',
    'that married the man all tattered and torn',
    'that kissed the maiden all forlorn',
    'that milked the cow with the crumpled horn',
    'that tossed the dog that worried the cat',
    'that killed the rat that ate the malt',
    'that lay in the house that Jack built.'
  ]
  LENGTH = FULL_POEM.length

  def verse(verse_no)
    binding.pry
    part_of_verse = FULL_POEM[(LENGTH - verse_no)..LENGTH].join('\n')
    "This is the#{part_of_verse.split('the')[1]}\n"
  end
end
