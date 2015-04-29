class Beer
  class Formatter
    def self.pluralize(amount, singular, plural)
      if amount == 1
        singular
      else
        plural
      end
    end

    def self.with_separators(lines)
      lines.join("\n") + "\n"
    end
  end

  class Song
    attr_reader :from, :to

    def initialize(from, to)
      @from = from
      @to = to
    end

    def verses
      from.downto(to).collect { |number|
        Verse.new(number)
      }
    end

    def to_s
      Formatter.with_separators(verses)
    end
  end

  class Verse
    attr_reader :current_number_of_bottles

    def initialize(number)
      @current_number_of_bottles = number
    end

    def join(separator)
      lines.join(separator)
    end

    def to_s
      Formatter.with_separators(self)
    end

    private

    def first_line
      bottles = pluralize_bottles(current_number_of_bottles)
      "#{bottles} of beer on the wall, #{bottles.downcase} of beer."
    end

    def lines
      [first_line, second_line]
    end

    def next_number_of_bottles
      current_number_of_bottles - 1
    end

    def pluralize_bottles(amount)
      if amount.zero?
        amount = "No more"
      end

      "#{amount} #{Formatter.pluralize(amount, "bottle", "bottles")}"
    end

    def second_line
      next_number_of_bottles = current_number_of_bottles - 1

      if current_number_of_bottles.zero?
        "Go to the store and buy some more, 99 bottles of beer on the wall."
      else
        subject = Formatter.pluralize(current_number_of_bottles, "it", "one")
        "Take #{subject} down and pass it around, #{pluralize_bottles(next_number_of_bottles).downcase} of beer on the wall."
      end
    end
  end

  def recite(from = 99, to = 0)
    `say '#{sing(from, to)}'`
  end

  def sing(from = 99, to = 0)
    Song.new(from, to).to_s
  end

  def verse(number)
    Verse.new(number).to_s
  end
end
