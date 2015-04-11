# class to produce cumulative song
class FoodChainSong
  def initialize
    @animals = %w(fly spider bird cat dog goat cow horse)
    @verses = [ "",
                "It wriggled and jiggled and tickled inside her.\n",
                "How absurd to swallow a bird!\n",
                "Imagine that, to swallow a cat!\n",
                "What a hog, to swallow a dog!\n",
                "Just opened her throat and swallowed a goat!\n",
                "I don't know how she swallowed a cow!\n",
                "She's dead, of course!\n" ]
  end

  def verse(n)
    case n
    when 1
      first(n) + last
    when 2
      first(n) + mid(1) + last
    when 3
      first(n) + spider + mid(1) + last
    when (4..7)
      first(n) + mid(n-1) + spider + mid(1) + last
    when 8
      first(n)
    end
  end

  def verses(first, last)
    (first..last).reduce('') { |song, n| song << verse(n) + "\n" }
  end

  def sing
    verses(1, 8)
  end

  private

  def spider
    "#{mid(2).gsub(/[\n\.]/,'')} #{@verses[1].sub('It','that')}"
  end

  def first(n)
    "I know an old lady who swallowed a #{@animals[n-1]}.\n#{@verses[n-1]}"
  end

  def mid(first)
    (4..6).include?(first) ? last = 3 : last = first
    first.downto(last).reduce('') do |result, i|
      result << "She swallowed the #{@animals[i]} to catch the #{@animals[i-1]}.\n"
    end
  end

  def last
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end
end
