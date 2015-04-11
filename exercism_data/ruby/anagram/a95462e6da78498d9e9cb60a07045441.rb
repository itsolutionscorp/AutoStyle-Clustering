class Anagram

  attr_reader :subject
  
  def initialize(subject)
    @subject = subject
  end

  def match(potential_anagrams)
    potential_anagrams.find_all do |potential_anagram|
      next if duplicate?(potential_anagram)
      potential_anagram if anagram?(potential_anagram)
    end
  end

  def duplicate?(potential_anagram)
    subject.downcase == potential_anagram.downcase
  end

  def anagram?(potential_anagram)
    valid_pieces == potential_pieces(potential_anagram)
  end

  def valid_pieces
    subject.downcase.split('').group_by { |char| char }
  end
  
  def potential_pieces(match)
    match.downcase.split('').group_by { |char| char }
  end

end
