class FoodChainSong

  def initialize
    @line = {
      fly: "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      spider: "It wriggled and jiggled and tickled inside her.\n",
      bird: "How absurd to swallow a bird!\n",
      cat: "Imagine that, to swallow a cat!\n",
      dog: "What a hog, to swallow a dog!\n",
      goat: "Just opened her throat and swallowed a goat!\n",
      cow: "I don't know how she swallowed a cow!\n",
      horse: "She's dead, of course!\n"
    }

    @animals = [
      :fly,
      :spider,
      :bird,
      :cat,
      :dog,
      :goat,
      :cow,
      :horse
    ]
  end

  def swallow(num)
    line = ''
    if num == 0 
      line += "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    else 
      line += "She swallowed the #{@animals[num]} to catch the #{@animals[num-1]}"
      if num == 2
        line += " that wriggled and jiggled and tickled inside her"
      end 
      line += ".\n"
    end
    line
  end

  def lyrics(num)
    num = num-1
    lyrics = "I know an old lady who swallowed a #{@animals[num]}.\n"
    lyrics +=  @line[@animals[num]] unless num == 0
    return lyrics if num == 7 

    (0..num).to_a.reverse.each do |i|
      i = i.abs
      lyrics += swallow(i) 
    end
    lyrics
  end

  def verse(number)
    lyrics(number)
  end

  def verses(start,stop)
    lyrics = ""
    (start..stop).each do |i|
      lyrics += verse(i)
      lyrics += "\n"
    end
    lyrics
  end
  
  def sing
    verses(1,8)
  end

end
