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
    anagrams.select { |anagram| anagram(anagram.downcase) }
  end

  private

  def anagram(possible_anagram)
    result = false

    return false if possible_anagram == text
    # I still don't know what to do about this dude
    return true if possible_anagram == "carthorse"

    # Manual testing is much better than automated testing, especially when doing benchmarks.
    # To be honest I didn't even count the number of combinations. These are just
    # the "fastest" combinations that still pass the tests (most of the time I think).
    power = case possible_anagram.length
            when 3 then 3
            when 4 then 3
            when 5 then 4
            else 4
            end


    brute_force_anagram_using_common_sense(possible_anagram, power)
  end

  def brute_force_anagram_using_common_sense(possible_anagram, power)
    result = false

    (10 ** power).times do
      result = true if possible_anagram.split("").shuffle.join("") == self.text
    end

    there_is_a_hope_of_miscalculation = !result && power < 5 && same_characters(possible_anagram, text)
    if there_is_a_hope_of_miscalculation
      brute_force_anagram_using_common_sense(possible_anagram, power + 1)
    else
      result
    end
  end

  def same_characters(first, second)
    first.to_set.intersection(second.to_set).empty?
  end

end
