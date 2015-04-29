#!/usr/bin/env ruby

# Exercism 25
# 99 Bottles of Beer

class BeerSong

  def verse(num)
    @beers = num
    if @beers == 0
      "No more bottles of beer on the wall, no more bottles of beer.\n"\
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{@beers} #{@beers == 1 ? 'bottle' : 'bottles'} of beer on the wall, #{@beers} #{@beers == 1 ? 'bottle' : 'bottles'} of beer.\n"\
      "Take #{@beers == 1 ? 'it' : 'one'} down and pass it around, #{@beers-1 == 0 ? 'no more' : @beers-1} #{@beers-1 == 1 ? 'bottle' : 'bottles'} of beer on the wall.\n"
    end
  end

  def verses(from_verse, to_verse)
    song = ''
    from_verse.downto(to_verse).each { |x| song << verse(x) + "\n"}
    song
  end

  def sing
    verses(99,0)
  end

end
