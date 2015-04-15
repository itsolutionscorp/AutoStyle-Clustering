class Anagram
  attr_accessor :subject
  
  def initialize(word="")
    @subject = word
  end
  
  def match(words)
    matches =  []
    words.each do |word|
      if (perfect_anagram?(word))
        matches.push(word)
      end
    end
    matches
  end
  
  private
  
  def perfect_anagram?(word)
    #perfect anagrams have the same number of letters as the original subject
    return false if @subject == word.downcase #don't allow identical words as anagrams
    subject_letter_counts = count_letters(@subject.downcase)
    letter_counts = count_letters(word.downcase)
    return false unless same_letters?(subject_letter_counts,letter_counts)
    letter_counts.all? {|letter,count| subject_letter_counts.key?(letter) && subject_letter_counts[letter] == count}
  end
  
  def same_letters?(letter_counts1, letter_counts2)
    letter_counts1.keys.length == letter_counts2.keys.length
  end

  def count_letters(word)
    #{"b"=>1,"d"=>3,"e"=>4}
    @letter_counts = {}
    word.each_char do |letter|
      @letter_counts[letter] = @letter_counts[letter].nil? ? 0 : @letter_counts[letter] + 1
    end
    @letter_counts
  end

end
