class House
  def self.recite
    new.recite
  end

  def recite
    result = ""
    (1..12).each { |i|
      phrase = ""
      count = 1
      while count <= i
        phrase = "#{what_it_is(count)}" + phrase
        count += 1
      end
      phrase = "This is " + phrase
      result += phrase
      }
      result.chomp
  end

  def what_it_is(n)
    case(n)
    when 1
      "the house that Jack built.\n\n"
    when 2
      "the malt\nthat lay in "
    when 3
      "the rat\nthat ate "
    when 4
      "the cat\nthat killed "
    when 5
      "the dog\nthat worried "
    when 6
      "the cow with the crumpled horn\nthat tossed "
    when 7
      "the maiden all forlorn\nthat milked "
    when 8
      "the man all tattered and torn\nthat kissed "
    when 9
      "the priest all shaven and shorn\nthat married "
    when 10
      "the rooster that crowed in the morn\nthat woke "
    when 11
      "the farmer sowing his corn\nthat kept "
    when 12
      "the horse and the hound and the horn\nthat belonged to "
    end
  end
end
