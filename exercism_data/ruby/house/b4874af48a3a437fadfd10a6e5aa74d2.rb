class House

  def initialize    
    @verse_parts=[]                                                            # => []
    @verse_parts[0]="This is "                                                 # => "This is "
    @verse_parts[1]=""                                                         # => ""
    @verse_parts[2]="the malt that lay in "                                    # => "the malt that lay in "
    @verse_parts[3]="the rat that ate "                                        # => "the rat that ate "
    @verse_parts[4]="the cat that killed "                                     # => "the cat that killed "
    @verse_parts[5]="the dog that worried "                                    # => "the dog that worried "
    @verse_parts[6]="the cow with the crumpled horn that tossed "              # => "the cow with the crumpled horn that tossed "
    @verse_parts[7]="the maiden all forlorn that milked "                      # => "the maiden all forlorn that milked "
    @verse_parts[8]="the man all tattered and torn that kissed "               # => "the man all tattered and torn that kissed "
    @verse_parts[9]="the priest all shaven and shorn that married "            # => "the priest all shaven and shorn that married "
    @verse_parts[10]="the rooster that crowed in the morn that woke "          # => "the rooster that crowed in the morn that woke "
    @verse_parts[11]="the farmer sowing his corn that kept "                   # => "the farmer sowing his corn that kept "
    @verse_parts[12]="the horse and the hound and the horn that belonged to "  # => "the horse and the hound and the horn that belonged to "
    @ending="the house that Jack built.\n"                                     # => "the house that Jack built.\n"
  end

  def verse(number)
    parts = ""                           # => "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
    (number).times do |i|                # => 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
      parts = @verse_parts[i+1] + parts  # => "", "", "the malt that lay in ", "", "the malt that lay in ", "the rat that ate the malt that lay in ", "", "", "the malt that lay in ", "", "the malt that lay in ", "the rat that ate the malt that lay in ", "", "the malt that lay in ", "the rat that ate the malt that lay in ", "the cat that killed the rat that ate the malt that lay in ", "", "the malt that lay in ", "the rat that ate the malt that lay in ", "the cat that killed the rat that ate t...
    end                                  # => 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    @verse_parts[0] + parts + @ending    # => "This is the house that Jack built.\n", "This is the malt that lay in the house that Jack built.\n", "This is the rat that ate the malt that lay in the house that Jack built.\n", "This is the house that Jack built.\n", "This is the malt that lay in the house that Jack built.\n", "This is the rat that ate the malt that lay in the house that Jack built.\n", "This is the cat that killed the rat that ate the malt that lay in the house that Jack built....
  end

  def verses(start,finish)
    verses = (start..finish).map do |n|  # => 1..3
      verse(n) + "\n"                    # => "This is the house that Jack built.\n\n", "This is the malt that lay in the house that Jack built.\n\n", "This is the rat that ate the malt that lay in the house that Jack built.\n\n"
    end                                  # => ["This is the house that Jack built.\n\n", "This is the malt that lay in the house that Jack built.\n\n", "This is the rat that ate the malt that lay in the house that Jack built.\n\n"]
    verses.join('')                      # => "This is the house that Jack built.\n\nThis is the malt that lay in the house that Jack built.\n\nThis is the rat that ate the malt that lay in the house that Jack built.\n\n"
  end

end
