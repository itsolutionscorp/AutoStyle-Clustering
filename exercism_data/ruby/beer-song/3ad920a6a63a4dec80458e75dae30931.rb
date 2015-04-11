class Beer
  def verse(verse_number)
    Verse.new(verse_number).text
  end

  def sing(from, to=0)
    verse_array(from, to).map{ |i| Verse.new(i).text }.join("\n") << "\n"
  end

  private

  def verse_array(from, to)
    (to..from).to_a.reverse
  end

  class Verse

    attr_reader :number

    def initialize(number)
      @number = number
    end

    def text
      [line_1, line_2].join("\n")
    end

    private

    def line_1
      case number
      when 0
        "No more bottles of beer on the wall, no more bottles of beer."
      else
        bottles_in_words = pluralize('bottle', number)
        "#{number} #{bottles_in_words} of beer on the wall, #{number} #{bottles_in_words} of beer."
      end
    end

    def line_2
      case number
      when 0
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
      when 1
        "Take it down and pass it around, no more bottles of beer on the wall.\n"
      else
        "Take one down and pass it around, #{next_number} #{pluralize('bottle', next_number)} of beer on the wall.\n"
      end
    end

    def pluralize(word, number)
      suffix = number === 1 ? nil : 's'
      "#{word}#{suffix}"
    end

    def next_number
      number - 1
    end
  end
end
