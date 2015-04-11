class Anagram 

  def initialize(input)
    @input = input.downcase
    @count = input.length
  end

  def match(matches)
    matches.select do |word|
      has_all_letters word.downcase
    end
  end

  def has_all_letters(word)
    return false if word.length > @count
    return false if @input == word
    @input.chars.each do |c|
      return false if !word.include?(c)
      word = word.sub(c, '')
    end
    true
  end
end
