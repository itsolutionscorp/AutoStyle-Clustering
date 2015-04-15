require 'ruby-debug'

class House
  attr_reader :rhyme
  
  def initialize
    @rhyme = {
      1 => "house that Jack built",
      2 => "malt that lay in the ",
      3 => "rat that ate the ",
      4 => "cat that killed the ",
      5 => "dog that worried the ",
      6 => "cow with the crumpled horn that tossed the ",
      7 => "maiden all forlorn that milked the ",
      8 => "man all tattered and torn that kissed the ",
      9 => "priest all shaven and shorn that married the ",
      10 => "rooster that crowed in the morn that woke the ",
      11 => "farmer sowing his corn that kept the ",
      12 => "horse and the hound and the horn that belonged to the "
    }
    @forms = ""
  end
  
  def verse(num)
    first_part + form_the_rhyme(num) + last_part
  end
  
  def verses(from, to)
    verse_collection = ""
    (from..to).each do |n| 
      verse_collection << verse(n)
      verse_collection << "\n"
      @forms = ""
    end
    verse_collection
  end
  
  private
  
  def form_the_rhyme(num)
    if num == 1
      @forms << rhyme[1]
    else
      @forms << rhyme[num]
      num = num - 1
      form_the_rhyme(num)
    end
    @forms
  end
  
  def first_part
    "This is the "
  end
  
  def last_part
    ".\n"
  end
end
