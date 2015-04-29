class Anagram
  def initialize(word)
    @word = sanitize_word(word)
  end

  def match(words)
    candidates = sanitize_candidates(words)

    candidates.map.with_index do |candidate, index| 
      words[index] if is_anagram?(candidate)
    end.compact!
  end
  
  private


  def sanitize_word(word)
    word.downcase.strip
  end

  def sanitize_candidates(words)
    words.map { |word| word.downcase.strip }
  end

  def is_anagram?(candidate)
    if @word == candidate
      false
    else
      candidate.chars.sort == @word.chars.sort
    end
  end
end
