class FoodChainSong
  ASIDE = Hash.new { |h, n| h.values.fetch(n.pred).dup }.merge(
    fly:    "I don't know why she swallowed the fly. Perhaps she'll die.\n",
    spider: "It wriggled and jiggled and tickled inside her.\n",
    bird:   "How absurd to swallow a bird!\n",
    cat:    "Imagine that, to swallow a cat!\n",
    dog:    "What a hog, to swallow a dog!\n",
    goat:   "Just opened her throat and swallowed a goat!\n",
    cow:    "I don't know how she swallowed a cow!\n",
    horse:  "She's dead, of course!\n"
  )
  FIRST = ->(n) { "I know an old lady who swallowed a #{ASIDE.keys[n - 1]}.\n" }
  CHASER = lambda do |n|
    (prey, desc), (chaser,) = ASIDE.to_a[n.pred, 2]
    "She swallowed the #{chaser} to catch the #{prey}" <<
      (desc.start_with?('It ') ? desc.sub('It', ' that') : ".\n")
  end

  def verse(n)
    chasers = n.pred.downto(1).map(&CHASER).join << ASIDE[1] unless
      n == 1 || n == ASIDE.count
    FIRST[n] << ASIDE[n] << (chasers || '')
  end

  def verses(from = 1, to = ASIDE.count)
    from.upto(to).map { |n| "#{verse n}\n" }.join
  end

  alias_method :sing, :verses
end
