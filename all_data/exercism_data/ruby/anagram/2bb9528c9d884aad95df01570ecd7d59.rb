class Anagram
  attr_accessor :subject
  
  def initialize(word="")
    @subject = word.downcase
  end
  
  def match(possible_anagrams)
    matches = possible_anagrams.select { |possible_anagram| anagram?(possible_anagram.downcase) }
  end
  
  private
  
  def anagram?(possible_anagram)
    unidentical_word?(possible_anagram) && same_letter_frequency?(possible_anagram)
  end
  
  def same_letter_frequency?(possible_anagram)
    letter_counts = count_letters(possible_anagram)
    subject_letter_counts = count_letters(@subject)
    letter_counts.keys.length == subject_letter_counts.keys.length && letter_counts.all? {|letter,count| subject_letter_counts.key?(letter) && subject_letter_counts[letter] == count}
  end
  
  def count_letters(word)
    #{"b"=>1,"d"=>3,"e"=>4}
    letter_counts = {}
    word.each_char do |letter|
      letter_counts[letter] = letter_counts[letter].nil? ? 0 : letter_counts[letter] + 1
    end
    letter_counts
  end

  def unidentical_word?(word)
    @subject != word.downcase
  end

end
