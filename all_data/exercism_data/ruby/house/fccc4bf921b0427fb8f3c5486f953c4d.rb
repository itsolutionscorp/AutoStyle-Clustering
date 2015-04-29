module House
  def self.recite
    Recital.new.to_s
  end

  NOUNS_AND_VERBS = {
    "house that Jack built" => "lay in",
    "malt" => "ate",
    "rat" => "killed",
    "cat" => "worried",
    "dog" => "tossed",
    "cow with the crumpled horn" => "milked",
    "maiden all forlorn" => "kissed",
    "man all tattered and torn" => "married",
    "priest all shaven and shorn" => "woke",
    "rooster that crowed in the morn" => "kept",
    "farmer sowing his corn" => "belonged to",
    "horse and the hound and the horn" => nil
  }

  class Stanza
    attr_reader :number_of_lines
    def initialize(number_of_lines)
      @number_of_lines = number_of_lines
      @lines = []
    end

    def to_s
      ([first_line] + body_lines).compact.join("\n") + ".\n"
    end

    private

    def first_line
      %{This is the #{word_pairs.pop.first}}
    end

    def body_lines
      [].tap do |lines|
        until word_pairs.empty?
          lines << next_pair {|noun, verb| %{that #{verb} the #{noun}}}
        end
      end
    end

    def next_pair
      yield word_pairs.pop
    end

    def word_pairs
      @word_pairs ||= NOUNS_AND_VERBS.take(number_of_lines)
    end

  end

  class Recital
    def to_s
      number_of_stanzas.times.map {|x| Stanza.new(x + 1).to_s}.join("\n")
    end

    private

    def number_of_stanzas
      NOUNS_AND_VERBS.count
    end
  end
end
