class Anagram

  attr_reader :word
  
  def initialize(word)
    @word = word
  end

  def match(words)
    matches = words.find_all do |potential_match|
      next if potential_match.length != word.length
      match = pieces.all? do |char|
        potential_match.downcase.include?(char)
      end
      word_group  = pieces.group_by { |char| char }
      match_group = match_pieces(potential_match).group_by { |char| char }
      if match && word_group == match_group && potential_match.downcase != word.downcase
        potential_match
      end
    end
  end

  def pieces
    word.downcase.split('')
  end
  
  def match_pieces(match)
    match.downcase.split('')
  end

end
