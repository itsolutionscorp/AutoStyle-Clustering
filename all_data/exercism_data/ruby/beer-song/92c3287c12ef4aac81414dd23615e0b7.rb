class Beer

  def sing(start_round, end_round = 0)
    song = []
    start_round.downto(end_round) do |round|
      song << verse(round)
    end
    song.join("\n") + "\n"
  end

  def verse(round)
    CountedVerse.new(round).verse
  end

  private

  # Done in the style of the previous example answers ;)
  class CountedVerse
    attr_accessor :round

    def initialize(round)
      self.round = round
    end

    def verse
      if round == 0
        last_verse
      else
        counted_verse
      end
    end

    private

    def article
      if round == 1
        'it'
      else
        'one'
      end
    end

    def bottle_phrase(number)
      "#{count number} #{number_for_bottle number}"
    end

    def count(number)
      if number == 0
        'no more'
      else
        number
      end
    end

    def counted_verse
      "#{bottle_phrase(round)} of beer on the wall, #{bottle_phrase round} of beer.\nTake #{article} down and pass it around, #{bottle_phrase round.pred} of beer on the wall.\n"
    end

    def last_verse
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end

    def number_for_bottle(number)
      if number == 1
        'bottle'
      else
        'bottles'
      end
    end
  end
end