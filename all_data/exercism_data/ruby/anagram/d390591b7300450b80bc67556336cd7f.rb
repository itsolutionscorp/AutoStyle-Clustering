class Anagram

  def initialize(input)
    @input = input
  end

  def match(candidates)
    candidates.find_all { |candidate| AnagramReferee.new(@input, candidate).legit_anagram? } 
  end

end

class AnagramReferee

  def initialize(reference, candidate)
    @reference = normalize reference
    @candidate = normalize candidate
  end

  def legit_anagram?
    not_identical? and use_same_letters?
  end

  private

    def not_identical?
      @reference != @candidate
    end

    def use_same_letters?
      sorted_letters_for(@candidate) == sorted_letters_for(@reference)
    end

    def sorted_letters_for(word)
      word.scan(regex).sort
    end

    def normalize(word)
      word.downcase
    end

    def regex
      /\w/
    end
end
