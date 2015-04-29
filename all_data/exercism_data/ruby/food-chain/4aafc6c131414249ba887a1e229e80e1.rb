class FoodChainSong
  def initialize
    @chain = [
      [:fly, nil], [:spider, nil],
      [:bird, "How absurd to swallow a bird"],
      [:cat,  "Imagine that, to swallow a cat"],
      [:dog,  "What a hog, to swallow a dog"],
      [:goat, "Just opened her throat and swallowed a goat" ],
      [:cow,  "I don't know how she swallowed a cow"],
      [:horse, "She's dead, of course" ]
    ]
  end

  def verse(para)
    text = ""
    animal, statement = @chain[para - 1]

    text += "I know an old lady who swallowed a #{animal}.\n"
    text += "#{statement}!\n" if statement
    return text if para == 8

    para.downto(4).each do |i|
      text += "She swallowed the #{@chain[i-1][0]} to catch the #{@chain[i-2][0]}.\n"
    end if para > 3

    if para > 2
      text += "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
      text += "She swallowed the spider to catch the fly.\n"
    elsif para == 2
      text += "It wriggled and jiggled and tickled inside her.\n"
      text += "She swallowed the spider to catch the fly.\n"
    end

    text += "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def verses(i, j)
    (i..j).map{|para| verse(para)}.join("\n") + "\n"
  end

  def sing
    verses(1, 8)
  end
end
