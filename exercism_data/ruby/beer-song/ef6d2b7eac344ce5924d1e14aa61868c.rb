class BeerSong
  private
  class << self
    def bottle(num)
      "bottle#{num!=1 ? 's' : ''}"
    end

    def describe_num(num)
      case num
        when 0
          'No more'
        when -1
          99
        else
          num
      end
    end

    def bottles(num)
      "#{describe_num(num)} #{bottle(num)}"
    end

    def of_beer
      'of beer'
    end
    
    def beer_wall
      "#{of_beer} on the wall"
    end

    def action(num)
      case num
        when 0
          'Go to the store and buy some more'
        else
          "Take #{num==1 ? 'it' : 'one'} down and pass it around"
      end
    end

    def verse(verse)
      %Q(#{bottles(verse)} #{beer_wall}, #{bottles(verse).downcase} #{of_beer}.\n#{action(verse)}, #{bottles(verse-1).downcase} #{beer_wall}.)
    end
  end

  public
  def verse(verse)
    self.class.verse(verse) << "\n"
  end

  def verses(from_verse, to_verse)
    from_verse.downto(to_verse).map { |x| verse(x) }.join("\n")+"\n"
  end

  def sing
    verses(99, 0)
  end
end
