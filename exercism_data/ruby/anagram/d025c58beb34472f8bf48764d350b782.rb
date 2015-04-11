class Anagram

  def initialize(subject)
    @subject = subject
  end

  def match(word_list)
    word_list.select {|potential_anagram| anagram?(potential_anagram)}
  end

  def normalized_subject
    @subject.downcase
  end

  def anagram?(potential_anagram)
    normalized_potential_anagram = potential_anagram.downcase
    normalized_potential_anagram != normalized_subject and normalized_potential_anagram.chars.sort == normalized_subject.chars.sort 
  end

end
