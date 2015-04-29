class Beer

  def sing(start_round, end_round = 0)
    [].tap {|song| add_verses(song, start_round, end_round)}.join('')
  end

  def verse(round)
    CountedVerse.new(round).verse
  end

  private

  def add_verses(song, start_round, end_round)
    start_round.downto(end_round) {|round| song << verse(round) + "\n" }
  end

  class CountedVerse
    attr_accessor :round

    def initialize(round)
      self.round = round
    end

    def verse
      counted_phrase + last_phrase
    end

    private

    def article
      round == 1 ? 'it' : 'one'
    end

    def bottle_phrase(number)
      "#{count number} #{number_for_bottle number} of beer"
    end

    def count(number)
      number.zero? ? 'no more' : number
    end

    def counted_phrase
      "#{bottle_phrase(round).capitalize} on the wall, #{bottle_phrase round}.\n"
    end

    def last_phrase
      (round.zero? ? store_phrase : second_phrase) + wall_phrase
    end

    def next_round
      (round - 1) % 100
    end

    def number_for_bottle(number)
      number == 1 ? 'bottle' : 'bottles'
    end

    def second_phrase
      "Take #{article} down and pass it around, "
    end

    def store_phrase
      "Go to the store and buy some more, "
    end

    def wall_phrase
      "#{bottle_phrase next_round} on the wall.\n"
    end
  end
end
