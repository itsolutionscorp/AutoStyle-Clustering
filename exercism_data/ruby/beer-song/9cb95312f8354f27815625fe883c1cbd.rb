class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(starting, ending)
    starting.downto(ending).reduce('') do |song, num_bottles|
      song << "#{verse(num_bottles)}\n"
    end
  end

  def verse(num_bottles)
    verse = case num_bottles
            when 2 then AntepenultimateVerse.new
            when 1 then PenultimateVerse.new
            when 0 then LastVerse.new
            else
              NormalVerse.new(num_bottles)
            end

    verse.sing
  end

  NormalVerse = Struct.new(:num_bottles) do
    def sing
      "#{summary_bottle_amount.capitalize} of beer on the wall, " \
        "#{summary_bottle_amount} of beer.\n#{action}, " \
        "#{result_bottle_amount} of beer on the wall.\n"
    end

    private

    def action
      "Take #{action_noun} down and pass it around"
    end

    def summary_bottle_amount
      "#{num_bottles} #{summary_bottle_inflection}"
    end

    def summary_bottle_inflection
      'bottles'
    end

    def action_noun
      'one'
    end

    def result_bottle_amount
      result_num_bottles = num_bottles - 1
      "#{result_num_bottles} #{result_bottle_inflection}"
    end

    def result_bottle_inflection
      'bottles'
    end
  end

  class AntepenultimateVerse < NormalVerse
    def initialize
      super(2)
    end

    private

    def result_bottle_inflection
      'bottle'
    end
  end

  class PenultimateVerse < NormalVerse
    def initialize
      super(1)
    end

    private

    def summary_bottle_inflection
      'bottle'
    end

    def action_noun
      'it'
    end

    def result_bottle_amount
      "no more #{result_bottle_inflection}"
    end
  end

  class LastVerse < NormalVerse
    def initialize
      super(0)
    end

    private

    def action
      "Go to the store and buy some more"
    end

    def summary_bottle_amount
      "no more #{summary_bottle_inflection}"
    end

    def result_bottle_amount
      "99 #{result_bottle_inflection}"
    end
  end
end
