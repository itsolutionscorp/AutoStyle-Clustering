class FoodChainSong

  def initialize
    @swallowed=['fly', 'spider', 'bird', 'cat', 'dog', 'goat', 'cow', 'horse']
    @yuck=["It wriggled and jiggled and tickled inside her.\n",
           "How absurd to swallow a bird!\n",
           "Imagine that, to swallow a cat!\n",
           "What a hog, to swallow a dog!\n",
           "Just opened her throat and swallowed a goat!\n",
           "I don't know how she swallowed a cow!\n"
    ]
  end

  def verse row
    header = "I know an old lady who swallowed a #{@swallowed.at(row - 1)}.\n"
    body = ''
    index = row - 1
    order = index
    unless (index == 0 || index == @swallowed.length-1)
      body += @yuck[index-1]
      while order > 0
        feed = @swallowed[order-1]
        if feed == 'spider'
          feed = 'spider that wriggled and jiggled and tickled inside her'
        end
        body << "She swallowed the #{@swallowed[order]} to catch the #{feed}.\n"
        order = order - 1
      end
    end

    tail = if (row == @swallowed.length)
             "She's dead, of course!\n"
           else
             "I don't know why she swallowed the fly. Perhaps she'll die.\n"
           end

    header + body + tail
  end

  def verses(a, b)
    collected = (a..b).collect do |i|
      verse(i) << "\n"
    end
    collected.join
  end

  def sing
    verses(1, @swallowed.length)
  end
end
