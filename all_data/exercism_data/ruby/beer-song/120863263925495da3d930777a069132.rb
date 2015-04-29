class BeerSong
    def song_builder (tweaks)
        one_verse = ''
        phrase = ''
        phrase << tweaks[:beers] << tweaks[:units] << "of beer"
        one_verse << phrase << " on the wall, " << phrase.downcase << ".\n"
          tweaks[:units] = 'bottles' if tweaks[:beers] == 1
          tweaks[:units] = 'bottle'if tweaks[:next_round] == 1
        one_verse << tweaks[:start_line_2] << tweaks[:next_round] << tweaks[:units] << "of beer on the wall.\n"
    end

    def many
      tweaks = {
                beers: @beers.to_s,
                units: ' bottles ',
                next_round: (@beers - 1).to_s,
                start_line_2: 'Take one down and pass it around, '
              }
      song_builder(tweaks)
    end

    def one
      tweaks = {
                beers: @beers.to_s,
                units: ' bottle ',
                next_round: 'no more',
                start_line_2: 'Take it down and pass it around, '
              }
      song_builder(tweaks)
    end

    def no
      tweaks = {
                beers: "No more ",
                units: ' bottles ',
                next_round: '99',
                start_line_2: 'Go to the store and buy some more, '
              }
      song_builder (tweaks)
    end

    def verse (beers)
      @beers = beers
        if @beers > 1
            result = self.many
        elsif @beers == 1
            result =  self.one
        elsif @beers == 0
            result = self.no
        end
        @beers -= 1
        result
    end

    def verses (beers, last)
      @beers = beers
      result = ""
      while @beers >= last
        result << verse(@beers) << "\n"
      end
      p result
    end

    def sing
        verses(99,0)
    end
end
