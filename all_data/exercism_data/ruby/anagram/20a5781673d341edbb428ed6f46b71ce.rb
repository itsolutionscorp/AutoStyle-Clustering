class Anagram

  def initialize(master)
    @master = master
  end

  def match(candidates)
    master_profile = char_profile(@master)
    [].tap do |match_list|
      candidates.each_with_object(match_list) do |word|
        if word.downcase != @master.downcase && master_profile == char_profile(word)
          match_list << word
        end
      end
    end

  end

  private

  def char_profile(word)
    #create a hash with char counts for the word
    word.downcase.chars.each_with_object(Hash.new(0)) { |char, count| count[char] += 1 }
  end

end
