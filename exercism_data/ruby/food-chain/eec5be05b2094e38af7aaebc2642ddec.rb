class FoodChainSong
  VERSES = [].tap do |verses|
    { fly:    "I don't know why she swallowed the fly. Perhaps she'll die.",
      spider: 'It wriggled and jiggled and tickled inside her.',
      bird:   'How absurd to swallow a bird!',
      cat:    'Imagine that, to swallow a cat!',
      dog:    'What a hog, to swallow a dog!',
      goat:   'Just opened her throat and swallowed a goat!',
      cow:    "I don't know how she swallowed a cow!",
      horse:  "She's dead, of course!"
    }.each_with_object('') do |(animal, aside), chasers|
      verses << "I know an old lady who swallowed a #{animal}.\n#{aside}\n"
      unless chasers.empty? || aside['dead']
        chasers.prepend("She swallowed the #{animal} to catch the ")
        verses.last << chasers << verses.first.lines.last
      end
      chasers.prepend("#{animal}#{aside.sub!(/^It/, ' that') || '.'}\n")
    end
  end

  def verse(n)
    VERSES[n.pred]
  end

  def verses(from = 1, to = VERSES.count)
    from.upto(to).map { |n| "#{verse n}\n" }.join
  end

  alias_method :sing, :verses
end
