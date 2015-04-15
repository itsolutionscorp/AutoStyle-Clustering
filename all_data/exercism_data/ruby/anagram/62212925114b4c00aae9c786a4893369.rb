class Anagram
  def initialize(detector)
    @detector = detector
  end
 
  def match(anagrams)
    create_matches(anagrams)
  end

  private

  def set_keys_array
    @keys = @detector.downcase.chars.sort
  end

  def create_matches(words)
    set_keys_array
    @matches = Array.new
    words.each do |a|
      if anagram?(a)  && not_same?(a)
        add_match(a)
      end
    end
    return @matches
  end

  def anagram?(w)
    w.downcase.chars.sort == @keys
  end

  def not_same?(w)
    w.downcase != @detector.downcase
  end

  def add_match(word)
    @matches << word
  end

end
