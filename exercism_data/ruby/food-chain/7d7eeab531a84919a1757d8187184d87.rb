class FoodChainSong
  def initialize
    @food = ['fly', 'spider', 'bird', 'cat', 'dog', 'goat', 'cow', 'horse']
  end

  def verse(num)
    num -= 1

    know(@food[num]) +
    ( num == 0 || num == 7 ? '' : through(num.downto(1)) ) +
    ( num == 7 ? die(@food[num]) : die(@food[0]) )
  end

  def verses(from, to = nil)
    to.nil? ? verse(from) : (from..to).map { |i| verse(i) + "\n" }.join
  end

  def sing
    verses(1, 8)
  end

  private
  
  def know(food)
    "I know an old lady who swallowed a #{food}.\n" +
    case food
    when 'spider'
      "It wriggled and jiggled and tickled inside her.\n"
    when 'bird'
      "How absurd to swallow a #{food}!\n"
    when 'cat'
      "Imagine that, to swallow a #{food}!\n"
    when 'dog'
      "What a hog, to swallow a #{food}!\n"
    when 'goat'
      "Just opened her throat and swallowed a #{food}!\n"
    when 'cow'
      "I don't know how she swallowed a #{food}!\n"
    else
      ''
    end
  end

  def swallow_food(food)
    "She swallowed the #{food}"
  end

  def catch_food(food)
    "to catch the #{food}" + ( food == 'spider' ? ' that wriggled and jiggled and tickled inside her.' : '.' )
  end

  def through(chain)
    chain.map { |i| swallow_food(@food[i]) + ' ' + catch_food(@food[i - 1]) + "\n" }.join
  end

  def die(food)
    food == 'horse' ? "She's dead, of course!\n" : "I don't know why she swallowed the #{food}. Perhaps she'll die.\n"
  end
end
