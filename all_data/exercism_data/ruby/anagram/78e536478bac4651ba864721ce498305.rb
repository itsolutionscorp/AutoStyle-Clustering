class Anagram

  def initialize(input)
    @input = input
    @anagram_ref = AnagramReferee.new(@input)
  end

  def match(candidates)
    candidates.find_all { |candidate| @anagram_ref.approves? candidate }
  end

end

class AnagramReferee

  def initialize(reference)
    @reference = normalize reference
  end

  def approves?(candidate)
    candidate = normalize candidate
    !identical_to_ref?(candidate) and uses_same_letters?(candidate)
  end

  private

    def identical_to_ref?(candidate)
      @reference == candidate
    end

    def uses_same_letters?(candidate)
      @reference.chars.sort == candidate.chars.sort
    end

    def normalize(word)
      word.downcase
    end

end
