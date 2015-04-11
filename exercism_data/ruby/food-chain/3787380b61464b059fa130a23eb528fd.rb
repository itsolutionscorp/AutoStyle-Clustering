class FoodChainSong
  def sing
    verses(1, 8)
  end

  def verses(start, finish)
    (start..finish).map(&method(:verse)).join("\n") << "\n"
  end

  def verse(n)
    "".tap do |str|
      str << preamble(n)
      str << description(n)
      if n < 8
        (1..n).reverse_each do |x|
          str << "#{reason(x)}"
        end
      end
    end
  end

  private

  CREATURES = {
    1 => "fly",
    2 => "spider",
    3 => "bird",
    4 => "cat",
    5 => "dog",
    6 => "goat",
    7 => "cow",
    8 => "horse"
  }

  REASONS = {
    1 => "I don't know why she swallowed the fly. Perhaps she'll die.\n",
    3 => "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
  }

  DESCRIPTIONS = {
    2 => "It wriggled and jiggled and tickled inside her.\n",
    3 => "How absurd to swallow a bird!\n",
    4 => "Imagine that, to swallow a cat!\n",
    5 => "What a hog, to swallow a dog!\n",
    6 => "Just opened her throat and swallowed a goat!\n",
    7 => "I don't know how she swallowed a cow!\n",
    8 => "She's dead, of course!\n"
  }
  

  def preamble(n)
    "I know an old lady who swallowed a #{creature(n)}.\n"
  end

  def creature(n)
    CREATURES.fetch(n)
  end

  def reason(n)
    REASONS.fetch(n){ "She swallowed the #{creature(n)} to catch the #{creature(n - 1)}.\n" }
  end

  def description(n)
    DESCRIPTIONS.fetch(n){ "" }
  end
end
