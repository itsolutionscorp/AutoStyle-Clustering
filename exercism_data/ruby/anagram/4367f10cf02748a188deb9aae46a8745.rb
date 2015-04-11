def conv(s)
  s.downcase.split(//).sort.join
end

# class Anagram
class Anagram
  def initialize(word)
    @word = conv(word)
  end

  def match(word_list)
    word_list                                       \
    .uniq       { |e| e.downcase }                   \
    .delete_if  { |e| e.downcase == @word.downcase }  \
    .select     { |e| conv(e) == @word }
  end
end
