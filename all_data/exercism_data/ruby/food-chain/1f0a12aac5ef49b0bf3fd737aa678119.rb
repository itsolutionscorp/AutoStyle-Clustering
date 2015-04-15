require 'pry'

class FoodChainSong
  ANIMALS = [              "",
                           {:name => "fly",    :intro => ""                                                  },
                           {:name => "spider", :intro => "It wriggled and jiggled and tickled inside her.\n" },
                           {:name => "bird",   :intro => "How absurd to swallow a bird!\n"                   },
                           {:name => "cat",    :intro => "Imagine that, to swallow a cat!\n"                 },
                           {:name => "dog",    :intro => "What a hog, to swallow a dog!\n"                   },
                           {:name => "goat",   :intro => "Just opened her throat and swallowed a goat!\n"    },
                           {:name => "cow",    :intro => "I don't know how she swallowed a cow!\n"           },
                           {:name => "horse",  :intro => "She's dead, of course!\n"                          }
  ]

  def sing
    self.verses(1, 8)
  end

  def verses(verse_1, verse_2)
    "#{verse(verse_1)}\n#{verse(verse_2)}\n"
  end

  def verse(n)
    verse_string = introduce_animal(n)
    if n == 8 then return verse_string end
    verse_string += why_swallow_animals(n)
  end

  def introduce_animal(n)
    animal = ANIMALS[n]
    result = "I know an old lady who swallowed a #{animal[:name]}.\n"
    result += animal[:intro]
  end

  def why_swallow_this_animal(n)
    if n == 1 then return "I don't know why she swallowed the fly. Perhaps she'll die.\n" end
    animal_1, animal_2 = ANIMALS[n], ANIMALS[n-1]
    spider = animal_2[:name] == "spider" ? " that wriggled and jiggled and tickled inside her" : ""
    "She swallowed the #{animal_1[:name]} to catch the #{animal_2[:name]}#{spider}.\n"
  end

  def why_swallow_animals(n)
    result = ""
    (1..n).to_a.reverse.each do |i|
      result += why_swallow_this_animal(i)
    end
    result
  end

end
