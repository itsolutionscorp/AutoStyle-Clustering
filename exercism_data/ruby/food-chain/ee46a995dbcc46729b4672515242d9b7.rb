class FoodChainSong
  class Snack
    attr_reader :name, :intro
    def initialize(name, intro, mention = name)
      @name = name
      @intro = intro
      @mention = mention
    end

    def mention
      @mention || @name
    end
  end

  FIRST_LINE = "I know an old lady who swallowed a %s.\n"
  SECOND_LINE = "%s\n"
  REPEATED_LINE = "She swallowed the %s to catch the %s.\n"
  LAST_LINE = "%s\n"

  SNACKS = [
    Snack.new("fly", "I don't know why she swallowed the fly. Perhaps she'll die."),
    Snack.new("spider", "It wriggled and jiggled and tickled inside her.", "spider that wriggled and jiggled and tickled inside her"),
    Snack.new("bird", "How absurd to swallow a bird!"),
    Snack.new("cat", "Imagine that, to swallow a cat!"),
    Snack.new("dog", "What a hog, to swallow a dog!"),
    Snack.new("goat", "Just opened her throat and swallowed a goat!"),
    Snack.new("cow", "I don't know how she swallowed a cow!"),
    Snack.new("horse", "She's dead, of course!")
  ]

  def verse(verse)
    lines(verse).join
  end

  def verses(from, to)
    verses = (from..to).map { |n| verse(n) }
    verses.join("\n") + "\n"
  end

  def sing
    verses(1, SNACKS.count)
  end

  private

  def lines(verse)
    lines = first_lines(verse) + repeated_lines(verse)
    lines << LAST_LINE % SNACKS.first.intro unless first_or_last_verse?(verse)
    lines
  end

  def first_lines(verse)
    snack = SNACKS[verse - 1]
    lines = []
    lines << FIRST_LINE % snack.name
    lines << SECOND_LINE % snack.intro
  end

  def repeated_lines(verse)
    snack_chain(verse).each_cons(2).map do |current, previous|
      REPEATED_LINE % [current.name, previous.mention]
    end
  end

  def snack_chain(verse)
    return [] if first_or_last_verse?(verse)
    SNACKS[0...verse].reverse
  end

  def first_or_last_verse?(verse)
    verse == 1 || verse == SNACKS.length
  end
end
