class FoodChainSong

  ANIMALS = %w(fly spider bird cat dog goat cow horse)

  SPIDER_RANT = "wriggled and jiggled and tickled inside her.\n"

  PHRASES = {
    :fly => "I don't know why she swallowed the fly. " +
            "Perhaps she'll die.\n", 
    :spider => "It #{SPIDER_RANT}",
    :bird => "How absurd to swallow a bird!\n",
    :cat => "Imagine that, to swallow a cat!\n",
    :dog => "What a hog, to swallow a dog!\n",
    :goat => "Just opened her throat and swallowed a goat!\n",
    :cow => "I don't know how she swallowed a cow!\n",
    :horse => "She's dead, of course!\n"
  }

  def sing
    verses(1, 8)
  end

  def verses(start, last = PHRASES.length)
    (start..last).reduce("") do |text, n|
      text << verse(n) << "\n"
    end
  end

  def verse(number)
    animal = ANIMALS[number - 1]
    text = "#{intro}#{animal}.\n#{PHRASES[animal.to_sym]}"

    case number
    when 1
      return text
    when 8
      return text
    when 2
      return text << coda
    else
      (number-1).downto(3).each_with_object(text) do |index, t|
        t << middle(index) << ".\n"
      end

      text << middle(2) << " that #{SPIDER_RANT}" << coda
    end
  end

  private
  def intro
    "I know an old lady who swallowed a "
  end

  def middle(index)
    "She swallowed the #{ANIMALS[index]} to catch the #{ANIMALS[index - 1]}"
  end

  def coda
    middle(1) << ".\n" << PHRASES[:fly]
  end

end
