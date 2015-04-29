class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.each_with_object([]) do |word, arr|
      wordd = word.downcase
      if word.length == @word.length && @word != wordd
        arr << word
        wordd.each_char do |c|
          if wordd.count(c) != @word.count(c) || !@word.include?(c)
            arr.delete(word)
          end
        end
      end
    end
  end
end
