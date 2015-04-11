class FoodChainSong

  def initialize
    @key_phrases = [{:animal => "fly",    :phrase => "I don't know why she swallowed the fly. Perhaps she'll die.\n"}, 
                    {:animal => "spider", :phrase => "wriggled and jiggled and tickled inside her.\n"},
                    {:animal => "bird",   :phrase => "How absurd to swallow a bird!\n"},
                    {:animal => "cat",    :phrase => "Imagine that, to swallow a cat!\n"},
                    {:animal => "dog",    :phrase => "What a hog, to swallow a dog!\n"},
                    {:animal => "goat",   :phrase => "Just opened her throat and swallowed a goat!\n"},
                    {:animal => "cow",    :phrase => "I don't know how she swallowed a cow!\n"},
                    {:animal => "horse",  :phrase => "She's dead, of course!\n"}]
                  
  end

  def sing
    verses(1, @key_phrases.length)
  end

  def verses(start_verse_num, end_verse_num)
    song = []
    for verse_num in start_verse_num..end_verse_num
      song.push(verse(verse_num), "\n")
    end
    song.join("")
  end

  def verse(verse_num)
    phrase_index = verse_num -1
    animal_count = 1
    verse = []

    begin
      follow_up_phrase = follow_up_phrase(phrase_index, animal_count)
      verse.push(swallow_line(phrase_index, animal_count), follow_up_phrase)
      animal_count += 1
      phrase_index -= 1
    end while phrase_index >= 0 && previous_animal(phrase_index) != "horse"

    verse.join("")
  end

  private

  def swallow_line(phrase_index, animal_count)
    animal1 = @key_phrases[phrase_index][:animal]
    animal2 = previous_animal(phrase_index)
    line = animal_count == 1 ? "I know an old lady who swallowed a #{animal1}" : "She swallowed the #{animal2} to catch the #{animal1}"
    line += " that #{@key_phrases[phrase_index][:phrase]}" if animal1 == "spider" && animal_count > 1 
    line += ".\n" unless animal1 == "spider" && animal_count > 1 
    line
  end

  def previous_animal(phrase_index)
    valid_range = phrase_index >= 0 && phrase_index < @key_phrases.length - 1
    valid_range ? @key_phrases[phrase_index + 1][:animal] : ""
  end

  def follow_up_phrase(phrase_index, animal_count)
    animal = @key_phrases[phrase_index][:animal]
    
    if phrase_index == 0 || animal_count == 1
      "#{'It ' if animal == 'spider'}" + @key_phrases[phrase_index][:phrase]
    else
      ""
    end
  end 
end
