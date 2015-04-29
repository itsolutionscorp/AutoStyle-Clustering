class BeerSong
      Lyrics = [
              '*',
              ' bottles of beer',
              ' on the wall',
              ', ',
              ".\n",
              'Take one down and pass it around, ',
              ' bottle of beer',
              'Take it down and pass it around, ',
              'No more',
              'no more',
              'Go to the store and buy some more, ',
              '#',
              '99'
            ]
      Cycles = [[0,0,11],[0,0,9],[8,9,12],[1,1,1],[2,4,2],[3,5,4],[1,1,6],[6,6,1],[3,7,4],[3,10,4]]

    def verse (beers)
      @beers = beers
          if beers > 2
            compose(Cycles[0].zip(Cycles[3],Cycles[4],Cycles[5]).flatten) #=> [0,1,2,3,0,1,4,5,11,1,2,4]
          elsif beers == 2
            compose(Cycles[0].zip(Cycles[6],Cycles[4],Cycles[5]).flatten) #=> [0,1,2,3,0,1,4,5,11,6,2,4]
          elsif beers == 1
            compose(Cycles[1].zip(Cycles[7],Cycles[4],Cycles[8]).flatten) #=> [0,6,2,3,0,6,4,7,9,1,2,4]
          elsif beers == 0
            compose(Cycles[2].zip(Cycles[3],Cycles[4],Cycles[9]).flatten) #=> [8,1,2,3,9,1,4,10,12,1,2,4]
          end
    end

    def verses(beers, empty)
      @beers = beers
      result = ''
          while @beers >= empty
            result << verse(@beers) << "\n"
            @beers -= 1
          end
      result
    end

    def sing
        verses(99,0)
    end

private
    def compose (ary)
      song = ary.each_with_object(''){|element, song| song << Lyrics[element]}
      song.tr('*', @beers.to_s).tr('#', (@beers - 1).to_s)
    end
end
