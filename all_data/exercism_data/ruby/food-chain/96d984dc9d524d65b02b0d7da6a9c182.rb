class FoodChainSong

  def verse(number)

    animal_hash = {
      1 => [ "fly" ],
      2 => [ "spider", "It wriggled and jiggled and tickled inside her.\n" ],
      3 => [ "bird", "How absurd to swallow a bird!\n" ],
      4 => [ "cat", "Imagine that, to swallow a cat!\n" ],
      5 => [ "dog", "What a hog, to swallow a dog!\n" ],
      6 => [ "goat", "Just opened her throat and swallowed a goat!\n" ],
      7 => [ "cow", "I don't know how she swallowed a cow!\n"],
      8 => [ "horse", "She's dead, of course!\n" ]
    }

    song = ""
    song += "I know an old lady who swallowed a #{animal_hash[number][0].to_s}.\n"
    song += animal_hash[number][1].to_s

    if number < 8
      (number).downto(1).each do |n|
        case
        when n == 2
          song += "She swallowed the spider to catch the fly.\n"
        when n == 3
          song += "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
        when n == 4
          song += "She swallowed the cat to catch the bird.\n"
        when n == 5
          song += "She swallowed the dog to catch the cat.\n"
        when n == 6
          song += "She swallowed the goat to catch the dog.\n"
        when n == 7
          song += "She swallowed the cow to catch the goat.\n"
        end
      end
      song += "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end

    song

  end


  def verses(number, number_2)
    verse(number) + "\n" + verse(number_2) + "\n"
  end

  
  def sing
    verses(1,8)
  end

end
