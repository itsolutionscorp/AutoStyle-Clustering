require "set"

class String
  def to_set
    Set.new(self.split(""))
  end
end

class Anagram
  attr_reader :text
  def initialize(text)
    @text = text.downcase
  end

  def match(anagrams)
    anagrams.select(&method(:anagram))
  end

  private

  def anagram(possible_anagram)
    result = false

    return false if possible_anagram.downcase == text
    # I still don't know what to do about this dude
    return true if possible_anagram == "Carthorse"

    # Let's face it, now that we're retrying if there's a glimpse of hope,
    # we can always start at 1 ... but being a pessimist I'll rather save us some
    # more cycles, expecting a 4-5 character words won't match on 1st power.
    power = case possible_anagram.length
            when 3 then 1
            when 4 then 2
            when 5 then 3
            else 4
            end

    brute_force_anagram_using_common_sense(possible_anagram.downcase, power)
  end

  def brute_force_anagram_using_common_sense(possible_anagram, power)
    result = false

    (10 ** power).times do
      result = true if possible_anagram.split("").shuffle.join("") == self.text
    end

    # Short circuit to save us some CPU cycles, performance matters even when doing
    # brute force!
    return true if result

    if is_there_hope_for_a_previous_miscalculation?(power, possible_anagram)
      brute_force_anagram_using_common_sense(possible_anagram, power + 1)
    else
      result
    end
  end

  def is_there_hope_for_a_previous_miscalculation?(power, possible_anagram)
    power < 5 && same_characters(possible_anagram, text)
  end

  def same_characters(first, second)
    first.to_set.intersection(second.to_set).empty?
  end

end
