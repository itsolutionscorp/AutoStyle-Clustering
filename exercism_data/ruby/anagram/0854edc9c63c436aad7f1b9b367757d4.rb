class Anagram

  def initialize(master)
    @master = master
  end

  def match(candidates)
    master_profile = char_profile(@master)
    match_list = []
    candidates.each do |word|
      if word.downcase != @master.downcase && master_profile == char_profile(word)
        match_list << word
      end
    end
    match_list
  end

  private

  def char_profile(word)
    #create a hash with char counts for the word
    word.downcase.chars.each_with_object(Hash.new(0)) { |char, count| count[char.downcase] += 1 }
  end

end
