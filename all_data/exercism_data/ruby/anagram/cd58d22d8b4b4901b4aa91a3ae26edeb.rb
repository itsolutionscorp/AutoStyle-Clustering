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
    @candidate = normalize candidate
    !identical? and use_same_letters?
  end

  private

    def identical?
      @reference == @candidate
    end

    def use_same_letters?
      @reference.chars.sort == @candidate.chars.sort
    end

    def normalize(word)
      word.downcase
    end

end
