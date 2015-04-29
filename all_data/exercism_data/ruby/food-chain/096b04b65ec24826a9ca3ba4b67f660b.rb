class FoodChainSong
  OPENING_PHRASE   = "I know an old lady who swallowed a _animal_."
  SWALLOWED_PHRASE = "She swallowed the _animal_ to catch the _last_animal_."
  CLOSING_PHRASE   = "I don't know why she swallowed the fly. Perhaps she'll die.\n"

  ANIMALS = [
    [ 'fly' ],
    [ 'spider', "It wriggled and jiggled and tickled inside her.", "spider that wriggled and jiggled and tickled inside her" ],
    [ 'bird',   "How absurd to swallow a bird!" ],
    [ 'cat',    "Imagine that, to swallow a cat!" ],
    [ 'dog',    "What a hog, to swallow a dog!" ],
    [ 'goat',   "Just opened her throat and swallowed a goat!" ],
    [ 'cow',    "I don't know how she swallowed a cow!" ],
    [ 'horse',  "She's dead, of course!\n" ]
  ]


  def verse(n)
    [].tap do |v|
      animal, animal_phrase = ANIMALS[n-1]

      v << OPENING_PHRASE.gsub(/_animal_/, animal)
      v << animal_phrase if animal_phrase

      # Special condition for the final animal (horse), do not bother with the swallowed phrases
      unless ANIMALS.size == n
        while n > 1
          n -= 1
          v << SWALLOWED_PHRASE.gsub(/_(animal|last_animal)_/, {'_animal_' => ANIMALS[n][0], '_last_animal_' => ANIMALS[n-1][2] || ANIMALS[n-1][0] })
        end

        v << CLOSING_PHRASE
      end
    end.join("\n")
  end

  def verses(from, to)
    (from..to).map { |n| verse(n) << "\n" }.join
  end

  def sing
    verses(1, ANIMALS.size)
  end
end
