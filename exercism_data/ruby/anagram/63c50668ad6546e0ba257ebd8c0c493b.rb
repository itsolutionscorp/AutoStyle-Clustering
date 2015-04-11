module Comparators
  def same_letters?(a, b)
    normalize(a) == normalize(b)
  end

  def different_word?(a, b)
    a.casecmp(b) != 0
  end

  private

    def normalize(word)
      word.downcase.chars.sort
    end
end

class Anagram
  include Comparators

  def initialize(subject)
    @subject = subject
  end

  def match(possibilities)
    possibilities.select do |candidate|
      same_letters?(candidate, @subject) &&
      different_word?(candidate, @subject)
    end
  end
end
